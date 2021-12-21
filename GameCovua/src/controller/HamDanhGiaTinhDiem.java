package controller;

import model.DuLieuCoVua;
import model.QuanCo;
import model.ViTri;

/**
 *
 * @author nhat_IT
 */

public class HamDanhGiaTinhDiem {   
    public int tinhDiem(ViTri viTri){        
        int diemTheCoNguoi = 0;
        int diemTheCoMay = 0;
        for(int i=1; i<viTri.cacLoaiQuanCoNguoi.length; i++){
            if(viTri.cacLoaiQuanCoNguoi[i] != null){
                QuanCo quanCo = viTri.cacLoaiQuanCoNguoi[i];
                int loaiQuanCo = quanCo.diem;
                diemTheCoNguoi += loaiQuanCo;

                switch(loaiQuanCo){
                    case QuanCo.TOT: 

                    	diemTheCoNguoi += DuLieuCoVua.BANG_DIEM_TOT_NGUOI[quanCo.viTriQuanCo];
                        break;
                    case QuanCo.MA: 

                    	diemTheCoNguoi += DuLieuCoVua.BANG_DIEM_MA_NGUOI[quanCo.viTriQuanCo];                        
                        break;
                    case QuanCo.TUONG: 

                        diemTheCoNguoi += DuLieuCoVua.BANG_DIEM_TUONG_NGUOI[quanCo.viTriQuanCo];
                        break;
                        
                    case QuanCo.XE: 
                    	   diemTheCoNguoi += DuLieuCoVua.BANG_DIEM_XE_NGUOI[quanCo.viTriQuanCo];
                    	 break;
                    case QuanCo.VUA: 
                    	   diemTheCoNguoi += DuLieuCoVua.BANG_DIEM_VUA_NGUOI[quanCo.viTriQuanCo];
                   	 break;
                    case QuanCo.HAU: 
                    	   diemTheCoNguoi += DuLieuCoVua.BANG_DIEM_HAU_NGUOI[quanCo.viTriQuanCo];
                   	 break;
                        
                        
                }
            }
            if(viTri.cacLoaiQuanCoMay[i] != null){
                QuanCo quanCo = viTri.cacLoaiQuanCoMay[i];
                int loaiQuanCo = quanCo.diem;
                diemTheCoMay += loaiQuanCo;
                switch(loaiQuanCo){
                    case QuanCo.TOT: 

                    	diemTheCoMay+=DuLieuCoVua.BANG_DIEM_TOT_MAY[quanCo.viTriQuanCo];
                        break;
                    case QuanCo.MA: 

                    	diemTheCoMay+=DuLieuCoVua.BANG_DIEM_MA_MAY[quanCo.viTriQuanCo];
                        break;
                    case QuanCo.TUONG: 

                    	diemTheCoMay+=DuLieuCoVua.BANG_DIEM_TUONG_MAY[quanCo.viTriQuanCo];
                        break;
                    case QuanCo.XE: 
                    	diemTheCoMay += DuLieuCoVua.BANG_DIEM_XE_MAY[quanCo.viTriQuanCo];
                 	 break;
                 case QuanCo.VUA: 
                	 diemTheCoMay += DuLieuCoVua.BANG_DIEM_VUA_MAY[quanCo.viTriQuanCo];
                	 break;
                 case QuanCo.HAU: 
                	 diemTheCoMay += DuLieuCoVua.BANG_DIEM_HAU_MAY[quanCo.viTriQuanCo];
                	 break;
                     
                     
                }
            }
        }
        return diemTheCoNguoi - diemTheCoMay;
    }    
}
