package controller;

import model.DuLieuCoVua;
import model.DiChuyen;
import model.QuanCo;
import model.ViTri;

/**
 *
 * @author nhat_IT
 */
public class GameCoVua {          

	ViTri viTri;
    QuanCo vuaNguoi;
    QuanCo vuaMay;
    
    public GameCoVua(ViTri viTri){
        vuaNguoi = viTri.cacLoaiQuanCoNguoi[8];
        vuaMay = viTri.cacLoaiQuanCoMay[8];
        this.viTri = viTri;
    }
    public int trangThai(int nguoiChoi){
        int trangThai = -1;
        MoRongNcDi moRongNcDi = new MoRongNcDi(viTri,nguoiChoi);
        moRongNcDi.moRongNuocDiMoi();
        ViTri[] listViTri = moRongNcDi.getDanhSachMoRong();
        if(listViTri.length == 0){
            if(kiemTraChieuTuong(nguoiChoi)) {
                trangThai = DuLieuCoVua.DACHIEUTUONG;
            }
            else trangThai = DuLieuCoVua.THUA;
        }
        return trangThai;
    }    
    public boolean vuaAnToan(int player, int viTriDau,int viTriCuoi){
        DiChuyen nuocDi = new DiChuyen(viTriDau,viTriCuoi);
        ViTri viTriQuanCo = new ViTri(viTri,nuocDi);  
        GameCoVua gameCovua = new GameCoVua(viTriQuanCo);   
        return !gameCovua.kiemTraChieuTuong(player);
    }
    public boolean kiemTraChieuTuong(int player){
        boolean chieuTuong = false;
        QuanCo vua = (player == DuLieuCoVua.NGUOI)?vuaNguoi:vuaMay;
        if(vua == null) return true;
        chieuTuong = totChieu(vua);
        if(!chieuTuong) chieuTuong = maChieu(vua);
        if(!chieuTuong) chieuTuong = tuongChieu(vua);
        if(!chieuTuong) chieuTuong = xeChieu(vua);
        if(!chieuTuong) chieuTuong = hauChieu(vua);
        if(!chieuTuong) chieuTuong = vuaChieu(vua);       
        return chieuTuong;
    }
    //vì 1 hàng  có 8 quân cờ và 2 ô trống 2 bên để giới hạn khung nên chỉ số ô sẽ được tính như sau<==>1 hàng 10 ô
   //     -21   -20	 -19
//   -12  -11   -10   -9  -8
//            \  |  /
//        -1 <-  X  -> +1   
//            /  |  \
//    +8   +9   +10  +11  +12
 //    	  +19	+20  +21
    private boolean totChieu(QuanCo vua){
        boolean dabiChieu = false;   
        int viTriQuanCo = vua.viTriQuanCo;
        if(vua == vuaNguoi){
            int totPhaiMay = viTri.banCo[viTriQuanCo-9];
            int totTraiMay = viTri.banCo[viTriQuanCo-11];
            if(totPhaiMay == DuLieuCoVua.KHUNG || totTraiMay == DuLieuCoVua.KHUNG) return false;
            if(totPhaiMay<0 && viTri.cacLoaiQuanCoMay[-totPhaiMay].diem == QuanCo.TOT)
            	dabiChieu = true;
            if(totTraiMay<0 && viTri.cacLoaiQuanCoMay[-totTraiMay].diem == QuanCo.TOT)
            	dabiChieu = true;
          
        }else{
            int totPhaiNguoi = viTri.banCo[viTriQuanCo+11];
            int totTraiNguoi = viTri.banCo[viTriQuanCo+9];
            if(totPhaiNguoi != DuLieuCoVua.KHUNG){
                if(totPhaiNguoi>0 && totPhaiNguoi != DuLieuCoVua.OTRONG && 
                        viTri.cacLoaiQuanCoNguoi[totPhaiNguoi].diem == QuanCo.TOT)
                    dabiChieu = true;
            }
            if(totTraiNguoi != DuLieuCoVua.KHUNG){
                if(totTraiNguoi>0 && totTraiNguoi != DuLieuCoVua.OTRONG && 
                        viTri.cacLoaiQuanCoNguoi[totTraiNguoi].diem == QuanCo.TOT)
                    dabiChieu = true;
            }
        }
        return dabiChieu;
    }
    private boolean maChieu(QuanCo vua){
        boolean dabiChieu = false;
        int viTriQuanCo = vua.viTriQuanCo;
        int[] ViTriMaChieuVua = {viTriQuanCo-21,viTriQuanCo+21,viTriQuanCo+19,viTriQuanCo-19,
            viTriQuanCo-12,viTriQuanCo+12,viTriQuanCo-8,viTriQuanCo+8};
        for(int viTriMa:ViTriMaChieuVua){
            int viTrimachieu = viTri.banCo[viTriMa];
            if(viTrimachieu == DuLieuCoVua.KHUNG) continue;
            if(vua == vuaNguoi){                
                if(viTrimachieu<0 && viTri.cacLoaiQuanCoMay[-viTrimachieu].diem == QuanCo.MA){
                    dabiChieu = true;
                    break;
                }
            }else{
                if(viTrimachieu>0 && viTrimachieu != DuLieuCoVua.OTRONG && 
                        viTri.cacLoaiQuanCoNguoi[viTrimachieu].diem == QuanCo.MA){
                    dabiChieu = true;
                    break;
                }
            }
        }
        return dabiChieu;
    }
    private boolean vuaChieu(QuanCo vua){
        boolean dabiChieu = false;
        int viTriQuanCo = vua.viTriQuanCo;
        int[] viTriVuaChieuVua = {viTriQuanCo+1,viTriQuanCo-1,viTriQuanCo+10,viTriQuanCo-10,
            viTriQuanCo+11,viTriQuanCo-11,viTriQuanCo+9,viTriQuanCo-9};
        for(int viTriVua:viTriVuaChieuVua){
            int viTrivuaChieu = viTri.banCo[viTriVua];
            if(viTrivuaChieu == DuLieuCoVua.KHUNG) continue;
            if(vua == vuaNguoi){                
                if(viTrivuaChieu<0 && viTri.cacLoaiQuanCoMay[-viTrivuaChieu].diem == QuanCo.VUA){
                    dabiChieu = true;
                    break;
                }
            }else{
                if(viTrivuaChieu>0 && viTrivuaChieu != DuLieuCoVua.OTRONG && 
                        viTri.cacLoaiQuanCoNguoi[viTrivuaChieu].diem == QuanCo.VUA){
                    dabiChieu = true;
                    break;
                }
            }
        }
        return dabiChieu;
    }
    private boolean tuongChieu(QuanCo vua){
        boolean dabiChieu = false;
        int[] chiSoHuongDi = {11,-11,9,-9};
        for(int i=0; i<chiSoHuongDi.length; i++){
            int viTriTuongChieuVua = vua.viTriQuanCo+chiSoHuongDi[i];
            while(true){
                int viTriTuongChieu = viTri.banCo[viTriTuongChieuVua];
                if(viTriTuongChieu == DuLieuCoVua.KHUNG) {
                    dabiChieu = false;
                    break;
                }
                if(vua == vuaNguoi){
                    if(viTriTuongChieu<0 && viTri.cacLoaiQuanCoMay[-viTriTuongChieu].diem == QuanCo.TUONG){
                        dabiChieu = true;
                        break;
                    }else 
                    	
                	if(viTriTuongChieu != DuLieuCoVua.OTRONG) break;
                }else if(vua == vuaMay){
                    if(viTriTuongChieu>0 && viTriTuongChieu != DuLieuCoVua.OTRONG && 
                            viTri.cacLoaiQuanCoNguoi[viTriTuongChieu].diem == QuanCo.TUONG){
                        dabiChieu = true;
                        break;
                    }else if(viTriTuongChieu != DuLieuCoVua.OTRONG) break;
                }
                viTriTuongChieuVua += chiSoHuongDi[i];
            }
            if(dabiChieu) break;
        }
        return dabiChieu;
    }    
    private boolean xeChieu(QuanCo vua){
        boolean dabiChieu = false;
        int[] chiSoHuongDi = {1,-1,10,-10};
        for(int i=0; i<chiSoHuongDi.length; i++){
            int viTriXeChieuVua = vua.viTriQuanCo+chiSoHuongDi[i];
            while(true){
                int viTriXeChieu = viTri.banCo[viTriXeChieuVua];
                if(viTriXeChieu == DuLieuCoVua.KHUNG) {
                    dabiChieu = false;
                    break;
                }
                if(vua == vuaNguoi){
                    if(viTriXeChieu<0 && viTri.cacLoaiQuanCoMay[-viTriXeChieu].diem == QuanCo.XE){
                        dabiChieu = true;
                        break;
                    }else 
                	if(viTriXeChieu != DuLieuCoVua.OTRONG) break;
                }else if(vua == vuaMay){
                    if(viTriXeChieu>0 && viTriXeChieu != DuLieuCoVua.OTRONG && 
                            viTri.cacLoaiQuanCoNguoi[viTriXeChieu].diem == QuanCo.XE){
                        dabiChieu = true;
                        break;
                    }else if(viTriXeChieu != DuLieuCoVua.OTRONG) break;
                }
                viTriXeChieuVua += chiSoHuongDi[i];
            }
            if(dabiChieu) break;
        }
        return dabiChieu;
    }    
    private boolean hauChieu(QuanCo vua){
        boolean dabiChieu = false;
        int[] chiSoHuongDi = {1,-1,10,-10,11,-11,9,-9};
        for(int i=0; i<chiSoHuongDi.length; i++){
            int viTriHauChieuVua = vua.viTriQuanCo+chiSoHuongDi[i];
            while(true){
                int viTriHauChieu = viTri.banCo[viTriHauChieuVua];
                if(viTriHauChieu == DuLieuCoVua.KHUNG) {
                    dabiChieu = false;
                    break;
                }
                if(vua == vuaNguoi){
                    if(viTriHauChieu<0 && viTri.cacLoaiQuanCoMay[-viTriHauChieu].diem == QuanCo.HAU){
                        dabiChieu = true;
                        break;
                    }else
                	if(viTriHauChieu != DuLieuCoVua.OTRONG) break;
                }else if(vua == vuaMay){
                    if(viTriHauChieu>0 && viTriHauChieu != DuLieuCoVua.OTRONG && 
                            viTri.cacLoaiQuanCoNguoi[viTriHauChieu].diem == QuanCo.HAU){
                        dabiChieu = true;
                        break;
                    }else if(viTriHauChieu != DuLieuCoVua.OTRONG) break;
                }
                viTriHauChieuVua += chiSoHuongDi[i];
            }
            if(dabiChieu) break;
        }
        return dabiChieu;
    }    public ViTri getViTri() {
		return viTri;
	}
	public void setViTri(ViTri viTri) {
		this.viTri = viTri;
	}
	public QuanCo getVuaNguoi() {
		return vuaNguoi;
	}
	public void setVuaNguoi(QuanCo vuaNguoi) {
		this.vuaNguoi = vuaNguoi;
	}
	public QuanCo getVuaMay() {
		return vuaMay;
	}
	public void setVuaMay(QuanCo vuaMay) {
		this.vuaMay = vuaMay;
	}
}
