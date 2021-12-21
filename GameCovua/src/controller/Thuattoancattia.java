package controller;

import model.DuLieuCoVua;
import model.ViTri;
import view.Maincovua;

/**
 *
 * @author nhat_IT
 */
public class Thuattoancattia {
    Maincovua mainCoVua;
    public int chieuSau;
    public Thuattoancattia(Maincovua mainCoVua){
        this.mainCoVua = mainCoVua;
    }
    public ViTri alphaBeta(int player, ViTri viTri, int alpha, int beta, int depth){
    	//vi trí ở đây là  thế cờ ban đầu
        if(depth == 0) return viTri;
        ViTri viTriTotNhat = null;
        MoRongNcDi nuocDiMoi = new MoRongNcDi(viTri,player);
        //mở rộng cac thế cờ mới 
        nuocDiMoi.moRongNuocDiMoi();
        ViTri[] ListViTri = nuocDiMoi.getDanhSachMoRong();
        if(ListViTri.length == 0) return viTri;    
        
        //chỉ mở rộng các nhánh trong khoảng  beta <=diem <=alpha
        
        HamDanhGiaTinhDiem hamDanhGiaTinhDiem = new HamDanhGiaTinhDiem();        
      //tất cả các nước đi có thể
        for(ViTri viTriMoi:ListViTri){
            if(viTriTotNhat == null) viTriTotNhat = viTriMoi;
            if(player == DuLieuCoVua.NGUOI){
            	//người chơi chọn diem min
            	//alpha là giá trị lớn nhất được chọn;
                ViTri viTriCoDoiKhang = alphaBeta(DuLieuCoVua.MAY,viTriMoi,alpha,beta,depth-1);                
                int diem = hamDanhGiaTinhDiem.tinhDiem(viTriCoDoiKhang);
                //if(diem>=beta && level > 5) return viTriMoi;
                if(diem>alpha){
                	//cắt nhánh tương ứng với điểm,khi điểm có giá trị  anpha thì min sẽ bỏ qua nước đi tuong ứng với điểm đó  không mở rộng theo thế cờ đó nữa
                    viTriTotNhat = viTriMoi;
                    //gán lại giá trị alpha
                    alpha = diem;
                  
                }
            }else{
            	//máy chơi chọn diem max
            	//beta là giá trị nhỏ nhất được chọn
                ViTri viTriCoDoiKhang = alphaBeta(DuLieuCoVua.NGUOI,viTriMoi,alpha,beta,depth-1);                
                if(new GameCoVua(viTriCoDoiKhang).kiemTraChieuTuong(DuLieuCoVua.NGUOI)){
                    return viTriMoi;
                }
                int diem = hamDanhGiaTinhDiem.tinhDiem(viTriCoDoiKhang);
                // giới hạn chiều sâu là 5 vì lớn hơn chạy khá chậm
                if(diem<=alpha && chieuSau > 5) return viTriMoi;
         //cắt nhánh tương ứng với điểm,khi điểm có giá trị tồi hơn beta thì max sẽ bỏ qua nước đi tuong ứng với điểm đó không mở rộng theo thế cờ đó nữa
                if(diem<beta){
                    viTriTotNhat = viTriMoi;
                    //gán lại giá trị beta
                    beta = diem;
                }              
            }
        }
        
//        System.out.println("đang ở chieu sâu "+depth);
//    	System.out.println("điểm bàn cờ này là: "+hamDanhGiaTinhDiem.tinhDiem(viTriTotNhat));
//        viTriTotNhat.displayBoard();
        return viTriTotNhat;
    }
}
