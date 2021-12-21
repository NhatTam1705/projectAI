package view;

import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;

import controller.GameCoVua;
import controller.Thuattoancattia;
import model.DuLieuCoVua;
import model.DiChuyen;
import model.QuanCo;
import model.ViTri;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.imageio.ImageIO;

public class Maincovua extends JFrame implements MouseListener {
	private boolean quanCoDangChon;
	private boolean chonCoTrang;
	private ThuVienAnh thuVienAnh = new ThuVienAnh();
	private Thuattoancattia thuatToanCatTia;
	private GameCoVua gameCoVua;
	private DiChuyen diChuyen = new DiChuyen();
	private Map<Integer, Image> images = new HashMap<Integer, Image>();
	private Map<Integer, Icon> icon_images = new HashMap<Integer, Icon>();
	JLabel taomoi_game, thoat, thongTin, logo, quaylui;
	JPanel main_panel = new JPanel(new BorderLayout());
	TuyChonPanel tuyChon;
	ViTri viTriTrenMaTrix;
	BanCopanel banCoPanel;
	int trangThai;
	boolean hoanDoiVuaXe;
	ThangChucChoTOTPanel thangChucPanel;
	Color mauNen = Color.decode("#6ad1d0");
	 List<ViTri> lichSudichuyen = new ArrayList<ViTri>();
	 int vitriundo;
	JSlider thanhlevel;

	public Maincovua() {
		super("GAME CỜ VUA GIẢI THUẬT CẮT TỈA AlPHA BETA " + DuLieuCoVua.VERSION);
		setContentPane(main_panel);
		viTriTrenMaTrix = new ViTri();
		thangChucPanel = new ThangChucChoTOTPanel(this);
		loadAnhMenu();
		loadAnhBanCo();
		banCoPanel = new BanCopanel();
		main_panel.add(taoMenu(), BorderLayout.EAST);

		thanhlevel = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
		JPanel levelPane = new JPanel();
		thanhlevel.setMajorTickSpacing(1);
		thanhlevel.setPaintTicks(true);
		thanhlevel.setPaintLabels(true);
		levelPane.add(thanhlevel);
//		Color.decode("#ff6873")
		levelPane.setBackground(mauNen);
		levelPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createTitledBorder("Chọn Chiều Sâu")));

		main_panel.add(levelPane, BorderLayout.SOUTH);
		main_panel.add(banCoPanel, BorderLayout.CENTER);
		main_panel.setBackground(mauNen);
		pack();
		Dimension size = getSize();
		size.height = 650;
		setSize(size);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				thoat();
			}
		});
	}

	public JPanel taoMenu() {
		logo = new JLabel(icon_images.get(DuLieuCoVua.LOGO));
		taomoi_game = new JLabel(icon_images.get(DuLieuCoVua.TAOMOIGAME_BUTTON));
		thongTin = new JLabel(icon_images.get(DuLieuCoVua.THONGTIN_BUTTON));
		quaylui = new JLabel(icon_images.get(DuLieuCoVua.QUAYLUI_BUTTON));
		thoat = new JLabel(icon_images.get(DuLieuCoVua.THOAT_BUTTON));
		taomoi_game.addMouseListener(this);
		thongTin.addMouseListener(this);
		quaylui.addMouseListener(this);
		thoat.addMouseListener(this);
		JPanel panel = new JPanel(new GridLayout(4, 1));
		panel.add(taomoi_game);
		 panel.add(quaylui);
		panel.add(thongTin);
		panel.add(thoat);

		panel.setBackground(mauNen);
		JPanel menu_panel = new JPanel(new BorderLayout());
		menu_panel.add(logo);
		menu_panel.setBackground(mauNen);
		menu_panel.add(panel, BorderLayout.SOUTH);
		menu_panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
		return menu_panel;
	}

	public void init() {
		chonCoTrang = tuyChon.btTrang.isSelected();
		diChuyen.viTriDau = -1;
		diChuyen.viTriDich = -1;
		viTriTrenMaTrix = new ViTri();
		viTriTrenMaTrix.taoBanCo(chonCoTrang);
		gameCoVua = new GameCoVua(viTriTrenMaTrix);
		loadAnhQuanCo();
		thangChucPanel.setIcons(chonCoTrang);
		banCoPanel.repaint();
		if (chonCoTrang)
			trangThai = DuLieuCoVua.NGUOI_DI;
		else
			trangThai = DuLieuCoVua.MAY_DI;
		hoanDoiVuaXe = false;

		 lichSudichuyen.clear();
		
		 vitriundo = 0;

		thuatToanCatTia.chieuSau = thanhlevel.getValue();
		System.out.println(thanhlevel.getValue());
		choiGame();
	}

	public void choiGame() {
		Thread t = new Thread() {
			public void run() {
				while (true) {
					switch (trangThai) {
					case DuLieuCoVua.NGUOI_DI:
						if (ketThucGame(DuLieuCoVua.NGUOI_DI)) {
							trangThai = DuLieuCoVua.DAKETTHUC;
							break;
						}
						break;
					case DuLieuCoVua.MAY_DI:
						if (ketThucGame(DuLieuCoVua.MAY)) {
							trangThai = DuLieuCoVua.DAKETTHUC;
							break;
						}
						diChuyen = thuatToanCatTia.alphaBeta(DuLieuCoVua.MAY, viTriTrenMaTrix, Integer.MIN_VALUE,
								Integer.MAX_VALUE, thanhlevel.getValue()).nuocDi;
						System.out.println("Buoc di tot nhat cho may la ");
				System.out.println(diChuyen.toString());

						trangThai = DuLieuCoVua.DICHUYEN_ANH;

						break;
					case DuLieuCoVua.DICHUYEN_ANH:
						diChuyenAnh();
							break;
					case DuLieuCoVua.CAP_NHAT:
						capNhatViTriQuanCo();

						break;
					case DuLieuCoVua.DAKETTHUC:
						return;
					}
					try {
						Thread.sleep(3);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}

	public boolean ketThucGame(int player) {
		int trangThai = gameCoVua.trangThai(player);
		boolean ketThuc = false;
		String color = "";
		if (player == DuLieuCoVua.MAY) {
			color = (chonCoTrang) ? "Trắng" : "Đen";
		} else
			color = (chonCoTrang) ? "Đen" : "Trắng";
		if (trangThai == DuLieuCoVua.DACHIEUTUONG) {
			hienThongBaothoat(color + " đã chiếu tướng hết đường đi nhé ");
			ketThuc = true;
		} else if (trangThai == DuLieuCoVua.THUA) {
			hienThongBaothoat("chơi hay quá ");
			ketThuc = true;
		}
		return ketThuc;
	}

	public void hienThongBaothoat(String message) {
		int option = JOptionPane.showOptionDialog(null, message, "Kết thúc game", 0, JOptionPane.PLAIN_MESSAGE, null,
				new Object[] { " Chơi lại", "thoát" }, " Chơi lại");
		if (option == 0) {
			tuyChon.setVisible(true);
		}
	}

	public void hienThongBao() {
		JOptionPane.showMessageDialog(null, "đợi tui đi đã\n", "Message", JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == thoat) {
			thoat();
		} else if (source == taomoi_game) {
			if (trangThai == DuLieuCoVua.MAY_DI) {
				hienThongBao();
				return;
			}
			if (tuyChon == null) {
				tuyChon = new TuyChonPanel(this);
				thuatToanCatTia = new Thuattoancattia(this);
			}
			tuyChon.setVisible(true);
		} else if (source == thongTin) {
			ThongTinPanel.hienThongBao();
		}
		 else if(source == quaylui){
		System.out.println("hien tai dang luu "+lichSudichuyen.size()+"ban co");
			 vitriundo = lichSudichuyen.size()-2;
		 System.out.println("quay lai ban co "+ vitriundo);
//		 
		 if(vitriundo>0){
		 quaylui();
		
		 xoa2thangcuoi();
		 luuLichSuDi();
//		 System.out.println("các ban co con lai sau khi xoa -##################################################");
//		 for (int i = 0; i <lichSudichuyen.size(); i++) {
//		 
//		 System.out.println("ban cờ thứ "+(i+1));
//		 lichSudichuyen.get(i).displayBoard();
//		 }
//		 System.out.println("-------------##################################################");
		
		
		
		
		
		 }
		 else{
		
		 System.out.println("het roi");
		 this.trangThai=DuLieuCoVua.DAKETTHUC;
		 this.init();
		 }
		 }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		if (source == taomoi_game) {
			taomoi_game.setIcon(icon_images.get(DuLieuCoVua.NEW_BUTTON2));
		} else if (source == thongTin) {
			thongTin.setIcon(icon_images.get(DuLieuCoVua.THONGTIN_BUTTON2));
		} else if (source == thoat) {
			thoat.setIcon(icon_images.get(DuLieuCoVua.THOAT_BUTTON2));
		} else if (source == quaylui) {
			quaylui.setIcon(icon_images.get(DuLieuCoVua.QUAYLUI_BUTTON2));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		if (source == taomoi_game) {
			taomoi_game.setIcon(icon_images.get(DuLieuCoVua.TAOMOIGAME_BUTTON));
		} else if (source == thongTin) {
			thongTin.setIcon(icon_images.get(DuLieuCoVua.THONGTIN_BUTTON));
		} else if (source == thoat) {
			thoat.setIcon(icon_images.get(DuLieuCoVua.THOAT_BUTTON));
		} else if (source == quaylui) {
			quaylui.setIcon(icon_images.get(DuLieuCoVua.QUAYLUI_BUTTON));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public class BanCopanel extends JPanel implements MouseListener {
		Image anh_quanCo;
		int movingX, movingY, desX, desY, deltaX, deltaY;

		public BanCopanel() {
			setPreferredSize(new Dimension(450, 495));
			setBackground(mauNen);
			addMouseListener(this);
		}

		@Override
		public void paintComponent(Graphics g) {
			if (viTriTrenMaTrix.banCo == null)
				return;
			super.paintComponent(g);
			g.drawImage(images.get(DuLieuCoVua.GAME_CO_VUA), 20, 36, this);
			g.drawImage(images.get(DuLieuCoVua.BOARD_IMAGE), 20, 65, this);
			for (int i = 0; i < viTriTrenMaTrix.banCo.length - 11; i++) {
				if (viTriTrenMaTrix.banCo[i] == DuLieuCoVua.KHUNG)
					continue;
				int x = i % 10;
				int y = (i - x) / 10;

				if (quanCoDangChon && i == diChuyen.viTriDau) {
					g.drawImage(images.get(DuLieuCoVua.GLOW), x * 45, y * 45, this);
				} else if (!quanCoDangChon && diChuyen.viTriDich == i
						&& (viTriTrenMaTrix.banCo[i] == DuLieuCoVua.OTRONG || viTriTrenMaTrix.banCo[i] < 0)) {
					g.drawImage(images.get(DuLieuCoVua.GLOW2), x * 45, y * 45, this);
				}

				if (viTriTrenMaTrix.banCo[i] == DuLieuCoVua.OTRONG)
					continue;

				if (trangThai == DuLieuCoVua.CAP_NHAT && i == diChuyen.viTriDau)
					continue;
				if (viTriTrenMaTrix.banCo[i] > 0) {
					int piece = viTriTrenMaTrix.cacLoaiQuanCoNguoi[viTriTrenMaTrix.banCo[i]].diem;
					g.drawImage(images.get(piece), x * 45, y * 45, this);
				} else {
					int piece = viTriTrenMaTrix.cacLoaiQuanCoMay[-viTriTrenMaTrix.banCo[i]].diem;
					g.drawImage(images.get(-piece), x * 45, y * 45, this);
				}
			}
			if (trangThai == DuLieuCoVua.CAP_NHAT) {
				g.drawImage(anh_quanCo, movingX, movingY, this);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (trangThai != DuLieuCoVua.NGUOI_DI)
				return;
			int viTriQuanCo = mapGiaTriBanCo(e.getY()) * 10 + mapGiaTriBanCo(e.getX());
			if (viTriTrenMaTrix.banCo[viTriQuanCo] == DuLieuCoVua.KHUNG)
				return;
			if ((!quanCoDangChon || viTriTrenMaTrix.banCo[viTriQuanCo] > 0) && viTriTrenMaTrix.banCo[viTriQuanCo] != DuLieuCoVua.OTRONG) {
				if (viTriTrenMaTrix.banCo[viTriQuanCo] > 0) {
					quanCoDangChon = true;
					diChuyen.viTriDau = viTriQuanCo;

				}
			} else if (quanCoDangChon && choPhepDiChuyen(viTriQuanCo)) {
				quanCoDangChon = false;

				diChuyen.viTriDich = viTriQuanCo;
				trangThai = DuLieuCoVua.DICHUYEN_ANH;

			}
			repaint();

		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

	public boolean choPhepDiChuyen(int huongDi) {
		int viTriDau = diChuyen.viTriDau;
		int viTriDich = viTriTrenMaTrix.banCo[huongDi];
		if (viTriDich == DuLieuCoVua.KHUNG)
			return false;
		if (!gameCoVua.vuaAnToan(DuLieuCoVua.NGUOI, viTriDau, huongDi)) {
			JOptionPane.showMessageDialog(this, "Bạn đang bị chiếu!!");
			return false;
		}
		boolean choPhepDi = false;
		int quanCo = viTriTrenMaTrix.cacLoaiQuanCoNguoi[viTriTrenMaTrix.banCo[viTriDau]].diem;
		switch (quanCo) {
		case QuanCo.TOT:
			if (huongDi == viTriDau - 10 && viTriDich == DuLieuCoVua.OTRONG)
				choPhepDi = true;
			if (huongDi == viTriDau - 20 && viTriTrenMaTrix.banCo[viTriDau - 10] == DuLieuCoVua.OTRONG
					&& viTriDich == DuLieuCoVua.OTRONG && viTriDau > 80)
				choPhepDi = true;
			if (huongDi == viTriDau - 9 && viTriDich < 0)
				choPhepDi = true;
			if (huongDi == viTriDau - 11 && viTriDich < 0)
				choPhepDi = true;
			break;
		case QuanCo.MA:
		case QuanCo.VUA:
			if (quanCo == QuanCo.VUA)
				choPhepDi = choPhepDoiViTriVuaVaXe(huongDi);
			int[] huongDiMoi = null;
			if (quanCo == QuanCo.MA)
				huongDiMoi = new int[] { viTriDau - 21, viTriDau + 21, viTriDau + 19, viTriDau - 19, viTriDau - 12,
						viTriDau + 12, viTriDau - 8, viTriDau + 8 };
			else
				huongDiMoi = new int[] { viTriDau + 1, viTriDau - 1, viTriDau + 10, viTriDau - 10, viTriDau + 11,
						viTriDau - 11, viTriDau + 9, viTriDau - 9 };
			for (int i = 0; i < huongDiMoi.length; i++) {
				if (huongDiMoi[i] == huongDi) {
					if (viTriDich == DuLieuCoVua.OTRONG || viTriDich < 0) {
						choPhepDi = true;
						break;
					}
				}
			}
			break;
		case QuanCo.TUONG:
		case QuanCo.XE:
		case QuanCo.HAU:
			int[] huongDiChuyen = null;
			if (quanCo == QuanCo.TUONG)
				huongDiChuyen = new int[] { 11, -11, 9, -9 };
			if (quanCo == QuanCo.XE)
				huongDiChuyen = new int[] { 1, -1, 10, -10 };
			if (quanCo == QuanCo.HAU)
				huongDiChuyen = new int[] { 1, -1, 10, -10, 11, -11, 9, -9 };
			for (int i = 0; i < huongDiChuyen.length; i++) {
				int viTriMoi = viTriDau + huongDiChuyen[i];
				choPhepDi = true;
				while (huongDi != viTriMoi) {
					viTriDich = viTriTrenMaTrix.banCo[viTriMoi];
					if (viTriDich != DuLieuCoVua.OTRONG) {
						choPhepDi = false;
						break;
					}
					viTriMoi += huongDiChuyen[i];
				}
				if (choPhepDi)
					break;
			}
			break;
		}

		return choPhepDi;
	}

	public boolean choPhepDoiViTriVuaVaXe(int viTriDich) {
		QuanCo vua = viTriTrenMaTrix.cacLoaiQuanCoNguoi[8];
		QuanCo xe_phai = viTriTrenMaTrix.cacLoaiQuanCoNguoi[6];
		QuanCo xe_trai = viTriTrenMaTrix.cacLoaiQuanCoNguoi[5];

		if (vua.dcPhepDiChuyen)
			return false;
		int viTriDau = diChuyen.viTriDau;

		if (xe_phai == null && xe_trai == null)
			return false;
		if (xe_phai != null && xe_phai.dcPhepDiChuyen && xe_trai != null && xe_trai.dcPhepDiChuyen)
			return false;

		if (chonCoTrang) {
			if (viTriDau != 95)
				return false;
			if (viTriDich != 97 && viTriDich != 93)
				return false;
			if (viTriDich == 97) {
				if (viTriTrenMaTrix.banCo[96] != DuLieuCoVua.OTRONG)
					return false;
				if (viTriTrenMaTrix.banCo[97] != DuLieuCoVua.OTRONG)
					return false;
				if (!gameCoVua.vuaAnToan(DuLieuCoVua.NGUOI, viTriDau, 96))
					return false;
				if (!gameCoVua.vuaAnToan(DuLieuCoVua.NGUOI, viTriDau, 97))
					return false;
			} else if (viTriDich == 93) {
				if (viTriTrenMaTrix.banCo[94] != DuLieuCoVua.OTRONG)
					return false;
				if (viTriTrenMaTrix.banCo[93] != DuLieuCoVua.OTRONG)
					return false;
				if (!gameCoVua.vuaAnToan(DuLieuCoVua.NGUOI, viTriDau, 94))
					return false;
				if (!gameCoVua.vuaAnToan(DuLieuCoVua.NGUOI, viTriDau, 93))
					return false;
			}
		} else {
			if (viTriDau != 94)
				return false;
			if (viTriDich != 92 && viTriDich != 96)
				return false;
			if (viTriDich == 92) {
				if (viTriTrenMaTrix.banCo[93] != DuLieuCoVua.OTRONG)
					return false;
				if (viTriTrenMaTrix.banCo[92] != DuLieuCoVua.OTRONG)
					return false;
				if (!gameCoVua.vuaAnToan(DuLieuCoVua.NGUOI, viTriDau, 93))
					return false;
				if (!gameCoVua.vuaAnToan(DuLieuCoVua.NGUOI, viTriDau, 92))
					return false;
			} else if (viTriDich == 96) {
				if (viTriTrenMaTrix.banCo[95] != DuLieuCoVua.OTRONG)
					return false;
				if (viTriTrenMaTrix.banCo[96] != DuLieuCoVua.OTRONG)
					return false;
				if (!gameCoVua.vuaAnToan(DuLieuCoVua.NGUOI, viTriDau, 95))
					return false;
				if (!gameCoVua.vuaAnToan(DuLieuCoVua.NGUOI, viTriDau, 96))
					return false;
			}
		}
	
		return hoanDoiVuaXe = true;
	}

	// map giá trị ban cờ và giao diện
	public int mapGiaTriBanCo(int giaTri) {
		return giaTri / 45;
	}

	public void diChuyenAnh() {
		int anh_QuanCo = 0;
		if (viTriTrenMaTrix.banCo[diChuyen.viTriDau] > 0) {
			anh_QuanCo = viTriTrenMaTrix.cacLoaiQuanCoNguoi[viTriTrenMaTrix.banCo[diChuyen.viTriDau]].diem;
		} 
		else {
			anh_QuanCo = -viTriTrenMaTrix.cacLoaiQuanCoMay[-viTriTrenMaTrix.banCo[diChuyen.viTriDau]].diem;
		}
		banCoPanel.anh_quanCo = images.get(anh_QuanCo);
		int x = diChuyen.viTriDau % 10;
		int y = (diChuyen.viTriDau - x) / 10;
		banCoPanel.desX = diChuyen.viTriDich % 10;
		banCoPanel.desY = (diChuyen.viTriDich - banCoPanel.desX) / 10;
		int dX = banCoPanel.desX - x;
		int dY = banCoPanel.desY - y;
		banCoPanel.movingX = x * 45;
		banCoPanel.movingY = y * 45;
		if (Math.abs(dX) > Math.abs(dY)) {
			if (dY == 0) {
				banCoPanel.deltaX = (dX > 0) ? 1 : -1;
				banCoPanel.deltaY = 0;
			} else {
				banCoPanel.deltaX = (dX > 0) ? Math.abs(dX / dY) : -(Math.abs(dX / dY));
				banCoPanel.deltaY = (dY > 0) ? 1 : -1;
			}
		} else {
			if (dX == 0) {
				banCoPanel.deltaY = (dY > 0) ? 1 : -1;
				banCoPanel.deltaX = 0;
			} else {
				banCoPanel.deltaX = (dX > 0) ? 1 : -1;
				banCoPanel.deltaY = (dY > 0) ? Math.abs(dY / dX) : -(Math.abs(dY / dX));
			}
		}
		trangThai = DuLieuCoVua.CAP_NHAT;
	}

	public void capNhatViTriQuanCo() {
		if (banCoPanel.movingX == banCoPanel.desX * 45 && banCoPanel.movingY == banCoPanel.desY * 45) {
			banCoPanel.repaint();
			int viTriDau = viTriTrenMaTrix.banCo[diChuyen.viTriDau];
			if (viTriDau > 0) {
				trangThai = DuLieuCoVua.MAY_DI;
			} else {
				if (diChuyen.viTriDich > 90 && diChuyen.viTriDich < 98
						&& viTriTrenMaTrix.cacLoaiQuanCoMay[-viTriDau].diem == QuanCo.TOT)
					thangChucTOT_may();
				if (ketThucGame(DuLieuCoVua.NGUOI))
					trangThai = DuLieuCoVua.DAKETTHUC;
				trangThai = DuLieuCoVua.NGUOI_DI;
			}
			viTriTrenMaTrix.update(diChuyen);

			 luuLichSuDi();

			if (viTriDau > 0) {
				if (hoanDoiVuaXe) {
					System.out.println("doi vua xe");
					hoanDoiVuaXe();
					
					trangThai = DuLieuCoVua.DICHUYEN_ANH;
					//trangThai =DuLieuCoVua.NGUOI_DI;
					
					
				} else if (diChuyen.viTriDich > 20 && diChuyen.viTriDich < 29
						&& viTriTrenMaTrix.cacLoaiQuanCoNguoi[viTriDau].diem == QuanCo.TOT) {
					thangChucQuanTot();
				}
			} else {
				if (ketThucGame(DuLieuCoVua.NGUOI)) {
					trangThai = DuLieuCoVua.DAKETTHUC;
					return;
				}
			}
			if (!hoanDoiVuaXe && trangThai != DuLieuCoVua.THANG_CHUC)
				lichSudichuyen.remove(lichSudichuyen.size()-1);
				 luuLichSuDi();
				if (hoanDoiVuaXe)
					hoanDoiVuaXe = false;
//			if (trangThai != DuLieuCoVua.DICHUYEN_ANH) {
//			}

		}

		banCoPanel.movingX += banCoPanel.deltaX;
		banCoPanel.movingY += banCoPanel.deltaY;
		banCoPanel.repaint();

	}

	public void thangChucQuanTot() {
		// hiện panel chọn thăng chức
		thangChucPanel.location = diChuyen.viTriDich;
		thangChucPanel.index = viTriTrenMaTrix.banCo[diChuyen.viTriDich];
		thangChucPanel.setVisible(true);
		trangThai = DuLieuCoVua.THANG_CHUC;
	}

	public void thangChucTOT_may() {
		// tự động đổi tốt thành hậu cho máy khi ở trạng thái thăng chức
		int viTriThangChuc = viTriTrenMaTrix.banCo[diChuyen.viTriDau];
		viTriTrenMaTrix.cacLoaiQuanCoMay[-viTriThangChuc] = new QuanCo(QuanCo.HAU, diChuyen.viTriDich);
	}

	public void hoanDoiVuaXe() {

		if (diChuyen.viTriDich == 97 || diChuyen.viTriDich == 96) {
			diChuyen.viTriDau = 98;
			diChuyen.viTriDich -= 1;
		} else if (diChuyen.viTriDich == 92 || diChuyen.viTriDich == 93) {
			diChuyen.viTriDau = 91;
			diChuyen.viTriDich += 1;
		}
		
				}

	 public void luuLichSuDi(){
	 viTriTrenMaTrix.nuocDi=null;
		 lichSudichuyen.add(new ViTri(viTriTrenMaTrix));

			
		 System.out.println("da luu "+lichSudichuyen.size()+" nuoc di");
		 System.out.println("da luu ban co ==============================");
		 viTriTrenMaTrix.displayBoard();
		 System.out.println("==============================");
//		 System.out.println("các bàn trong danh sach-##################################################");
//		 for (int i = 0; i <lichSudichuyen.size(); i++) {
//		 
//		 System.out.println("ban cờ thứ "+(i+1));
//		 lichSudichuyen.get(i).displayBoard();
//		 }
//		 System.out.println("-------------##################################################");
//	

	 }
	 public void quaylui(){
		//diChuyen=null;
	 if(lichSudichuyen.size()>=1){
	 vitriundo--;
	 ViTri vt=lichSudichuyen.get(vitriundo);
	 this.viTriTrenMaTrix = vt;
	this.gameCoVua.setViTri(vt);
		trangThai=DuLieuCoVua.NGUOI_DI;
	 banCoPanel.repaint();
	

	
	 System.out.println("so lan lui lai "+lichSudichuyen.size()/2);
//	 System.out.println("các bàn da luu -##################################################");
//	 for (int i = 0; i <lichSudichuyen.size(); i++) {
//	 
//	 System.out.println("ban cờ thứ "+(i+1));
//	 lichSudichuyen.get(i).displayBoard();
//	 }
//	 System.out.println("-------------##################################################");
//System.out.println("đã quay lại vi trí bàn cờ-------------------------------------------------------------");
//	 viTriTrenMaTrix.displayBoard();
//	 System.out.println("-------------------------------------------------------------");
	 }

	 }
	 public void xoa2thangcuoi(){
		 int s=lichSudichuyen.size()-1;
		 System.out.println("da xoa ban co nay");
		 lichSudichuyen.get(s).displayBoard();
	lichSudichuyen.remove(s);
	 System.out.println("da xoa ban co nay");
	 lichSudichuyen.get(s-1).displayBoard();
	lichSudichuyen.remove(s-1);
	 System.out.println("da xoa ban co nay");
	 lichSudichuyen.get(s-2).displayBoard();
	lichSudichuyen.remove(s-2);
	

	 }
	
	public void loadAnhQuanCo() {
		char[] resource_keys = { 'p', 'n', 'b', 'r', 'q', 'k' };
		int[] images_keys = { QuanCo.TOT, QuanCo.MA, QuanCo.TUONG, QuanCo.XE, QuanCo.HAU, QuanCo.VUA };
		try {
			for (int i = 0; i < resource_keys.length; i++) {
				images.put(images_keys[i],
						ImageIO.read(thuVienAnh.getResource((chonCoTrang ? "trang" : "den") + resource_keys[i])));
				images.put(-images_keys[i],
						ImageIO.read(thuVienAnh.getResource((chonCoTrang ? "den" : "trang") + resource_keys[i])));
			
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void loadAnhBanCo() {
		try {
			images.put(DuLieuCoVua.BOARD_IMAGE, ImageIO.read(thuVienAnh.getResource("banco")));
			images.put(DuLieuCoVua.GLOW, ImageIO.read(thuVienAnh.getResource("glow")));
			images.put(DuLieuCoVua.GLOW2, ImageIO.read(thuVienAnh.getResource("glow2")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void loadAnhMenu() {
		// icon_images.put(DuLieuCoVua.LOGO, new ImageIcon(thuVienAnh.getResource("logo")));
		icon_images.put(DuLieuCoVua.TAOMOIGAME_BUTTON, new ImageIcon(thuVienAnh.getResource("new_game")));
		icon_images.put(DuLieuCoVua.NEW_BUTTON2, new ImageIcon(thuVienAnh.getResource("new_game_hover")));
		icon_images.put(DuLieuCoVua.THOAT_BUTTON, new ImageIcon(thuVienAnh.getResource("thoat")));
		icon_images.put(DuLieuCoVua.THOAT_BUTTON2, new ImageIcon(thuVienAnh.getResource("thoat_hover")));
		icon_images.put(DuLieuCoVua.QUAYLUI_BUTTON, new ImageIcon(thuVienAnh.getResource("quaylui")));
		icon_images.put(DuLieuCoVua.QUAYLUI_BUTTON2, new ImageIcon(thuVienAnh.getResource("quaylui_hover")));
		icon_images.put(DuLieuCoVua.THONGTIN_BUTTON, new ImageIcon(thuVienAnh.getResource("thongtin")));
		icon_images.put(DuLieuCoVua.THONGTIN_BUTTON2, new ImageIcon(thuVienAnh.getResource("thongtin_hover")));
	}

	public void thoat() {
		int option = JOptionPane.showConfirmDialog(null, "Chơi không lại nên không chơi nữa hả?", " Thoát",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.YES_OPTION)
			System.exit(0);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					boolean nimbusFound = false;
					for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if (info.getName().equals("Nimbus")) {
							UIManager.setLookAndFeel(info.getClassName());
							nimbusFound = true;
							break;
						}
					}
					if (!nimbusFound) {
						int option = JOptionPane.showConfirmDialog(null,
								" không hỗ trợ UI này\n" + "ban muốn tiếp tục không?", "cảnh báo",
								JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if (option == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					}

					//đổi cách hiển thị giao diện máy mac
					// System.setProperty(
					// "Quaqua.tabLayoutPolicy","wrap"
					// );
					// UIManager.setLookAndFeel(
					// "ch.randelshofer.quaqua.QuaquaLookAndFeel");
					//

					Maincovua mcg = new Maincovua();
					// mcg.pack();
					mcg.setLocationRelativeTo(null);
					mcg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mcg.setResizable(false);
					mcg.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getStackTrace());
					e.printStackTrace();
				}
			}
		});
	}
}
