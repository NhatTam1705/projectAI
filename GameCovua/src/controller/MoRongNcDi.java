    package controller;
import java.util.ArrayList;
import java.util.List;

import model.DuLieuCoVua;
import model.DiChuyen;
import model.QuanCo;
import model.ViTri;

public class MoRongNcDi {    
    ViTri vitTri;
    int nguoiChoi;
    List<ViTri> danhSachVitri = new ArrayList<ViTri>();   
    GameCoVua gs;
    
    public MoRongNcDi(ViTri viTri, int nguoiChoi){
        this.vitTri = viTri;
        this.nguoiChoi = nguoiChoi;
        this.gs = new GameCoVua(viTri);
    }
    public ViTri[] getDanhSachMoRong(){                
        return danhSachVitri.toArray(new ViTri[danhSachVitri.size()]);
    }
    public void moRongNuocDiMoi(){
        if(nguoiChoi == DuLieuCoVua.NGUOI){
            for(int i=1; i<vitTri.cacLoaiQuanCoNguoi.length; i++){                
                QuanCo quanCo = vitTri.cacLoaiQuanCoNguoi[i];       
                if(quanCo == null) continue;
                switch(quanCo.diem){
                    case QuanCo.TOT:
                        nuocDiMoiQuanTot_nguoi(quanCo);
                        break;
                    case QuanCo.MA:
                        nuocDiMoiQuanMa_Nguoi(quanCo);
                        break;
                    case QuanCo.VUA:
                        nuocDiMoiQuanVua_Nguoi(quanCo);
                        break;
                    case QuanCo.TUONG:
                        nuocDiMoiQuanTuong_Nguoi(quanCo);
                        break;
                    case QuanCo.XE:
                        nuocDiMoiQuanXe_Nguoi(quanCo);
                        break;
                    case QuanCo.HAU:
                        nuocDiMoiQuanHau_Nguoi(quanCo);
                }
            }
        }else{
            for(int i=1; i<vitTri.cacLoaiQuanCoMay.length; i++){
                QuanCo quanCo = vitTri.cacLoaiQuanCoMay[i];    
                if(quanCo == null) continue;
                switch(quanCo.diem){
                    case QuanCo.TOT:
                        nuocDiMoiQuanTot_May(quanCo);
                        break;
                    case QuanCo.MA:
                        nuocDiMoiQuanMa_May(quanCo);
                        break;
                    case QuanCo.VUA:
                        nuocDiMoiQuanVua_May(quanCo);
                        break;
                    case QuanCo.TUONG:
                        nuocDiMoiQuanTuong_May(quanCo);
                        break;
                    case QuanCo.XE:
                        nuocDiMoiQuanXe_May(quanCo);
                        break;
                    case QuanCo.HAU:
                        nuocDiMoiQuanHau_May(quanCo);
                }
            }
        }
    }
    public void nuocDiMoiQuanTot_nguoi(QuanCo tot){        
        int viTri = tot.viTriQuanCo;
        // th??? ??i l??n 1 ??
        int vitriMoi = vitTri.banCo[viTri-10];
        if(vitriMoi != DuLieuCoVua.KHUNG){
        	//ch??a ?????ng t?????ng
            if(vitriMoi == DuLieuCoVua.OTRONG && gs.vuaAnToan(DuLieuCoVua.NGUOI,viTri,viTri-10)) {
            	// ??i l??n n???u tr???ng v?? an to??n th?? c?? th??? l??n 1 ?? 
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,viTri-10)));
                
            }
        }
        
        if(viTri > 80 && vitriMoi == DuLieuCoVua.OTRONG && 
                vitTri.banCo[viTri-20] == DuLieuCoVua.OTRONG && gs.vuaAnToan(DuLieuCoVua.NGUOI,viTri,viTri-20)) {    
        	//c?? th??? l??n 2 ??
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,viTri-20)));
        }
        
        int viTriCheoPhai = vitTri.banCo[viTri-9];
        if(viTriCheoPhai != DuLieuCoVua.KHUNG) {
            if(viTriCheoPhai<0 && gs.vuaAnToan(DuLieuCoVua.NGUOI,viTri,viTri-9))
            	//c?? th??? ??i ch??o ph???i
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,viTri-9)));
        }
        int viTriCheoTrai = vitTri.banCo[viTri-11];
        if(viTriCheoTrai != DuLieuCoVua.KHUNG) {
            if(viTriCheoTrai<0 && gs.vuaAnToan(DuLieuCoVua.NGUOI,viTri,viTri-11))
            	//c?? th??? di ch??o tr??i
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,viTri-11)));
        }        
    }    
    public void nuocDiMoiQuanTot_May(QuanCo tot){      
        int viTri = tot.viTriQuanCo;
        int viTriMoi = vitTri.banCo[viTri+10];
        if(viTriMoi != DuLieuCoVua.KHUNG){
            if(viTriMoi == DuLieuCoVua.OTRONG && gs.vuaAnToan(DuLieuCoVua.MAY,viTri,viTri+10)){ 
//            	c?? th??? l??n 1 ?? 
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,viTri+10)));
            }
        }
        
        if(viTri < 39 && viTriMoi == DuLieuCoVua.OTRONG && 
                vitTri.banCo[viTri+20] == DuLieuCoVua.OTRONG && gs.vuaAnToan(DuLieuCoVua.MAY,viTri,viTri+20)) {  
        	//c?? th??? l??n 2 ??
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,viTri+20)));
        }
        
        int viTriCheoPhai = vitTri.banCo[viTri+11];
        if(viTriCheoPhai != DuLieuCoVua.KHUNG) {
            if(viTriCheoPhai>0 && viTriCheoPhai != DuLieuCoVua.OTRONG &&
                    gs.vuaAnToan(DuLieuCoVua.MAY,viTri,viTri+11))
            	//c?? th??? ??i ch??o ph???i
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,viTri+11)));
        }
        int viTriCheoTrai = vitTri.banCo[viTri+9];
        if(viTriCheoTrai != DuLieuCoVua.KHUNG) {
            if(viTriCheoTrai>0 && viTriCheoTrai != DuLieuCoVua.OTRONG &&
                    gs.vuaAnToan(DuLieuCoVua.MAY,viTri,viTri+9))
            	//c?? th??? di ch??o tr??i
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,viTri+9)));
        }        
    }    
    public void nuocDiMoiQuanMa_Nguoi(QuanCo ma){
        int viTri = ma.viTriQuanCo;
        int[] huongDiMoi = {viTri-21,viTri+21,viTri+19,viTri-19,
            viTri-12,viTri+12,viTri-8,viTri+8};
        for(int i=0; i<huongDiMoi.length; i++){
            int viTriMoi = vitTri.banCo[huongDiMoi[i]];
            if(viTriMoi != DuLieuCoVua.KHUNG && (viTriMoi == DuLieuCoVua.OTRONG || viTriMoi<0)
                     && gs.vuaAnToan(DuLieuCoVua.NGUOI,viTri,huongDiMoi[i]))
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,huongDiMoi[i])));
        }
    }
    public void nuocDiMoiQuanMa_May(QuanCo ma){
        int viTri = ma.viTriQuanCo;
        int[] huongDiMoi = {viTri-21,viTri+21,viTri+19,viTri-19,
            viTri-12,viTri+12,viTri-8,viTri+8};
        for(int i=0; i<huongDiMoi.length; i++){
            int viTriMoi = vitTri.banCo[huongDiMoi[i]];
            if(viTriMoi != DuLieuCoVua.KHUNG && (viTriMoi == DuLieuCoVua.OTRONG || viTriMoi>0) &&
                    gs.vuaAnToan(DuLieuCoVua.MAY,viTri,huongDiMoi[i])){
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,huongDiMoi[i])));
            }
        }
    }
    public void nuocDiMoiQuanVua_Nguoi(QuanCo vua){
        int viTri = vua.viTriQuanCo;
        int[] huongDiMoi = {viTri+1,viTri-1,viTri+10,viTri-10,
            viTri+11,viTri-11,viTri+9,viTri-9};
        for(int i=0; i<huongDiMoi.length; i++){
            int viTriMoi = vitTri.banCo[huongDiMoi[i]];
            if(viTriMoi != DuLieuCoVua.KHUNG && (viTriMoi == DuLieuCoVua.OTRONG || viTriMoi<0)
                    && gs.vuaAnToan(DuLieuCoVua.NGUOI,viTri,huongDiMoi[i])){
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,huongDiMoi[i])));
            }
        }
    }
    public void nuocDiMoiQuanVua_May(QuanCo vua){
        int viTri = vua.viTriQuanCo;
        int[] huongDiMoi = {viTri+1,viTri-1,viTri+10,viTri-10,
            viTri+11,viTri-11,viTri+9,viTri-9};
        for(int i=0; i<huongDiMoi.length; i++){
            int viTriMoi = vitTri.banCo[huongDiMoi[i]];
            if(viTriMoi != DuLieuCoVua.KHUNG && (viTriMoi == DuLieuCoVua.OTRONG || viTriMoi>0) &&
                    gs.vuaAnToan(DuLieuCoVua.MAY,viTri,huongDiMoi[i])){
                danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,huongDiMoi[i])));
            }
        }
    }
    public void nuocDiMoiQuanTuong_Nguoi(QuanCo tuong){
        int viTri = tuong.viTriQuanCo;
        int[] huongDi = {11,-11,9,-9};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];            
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == DuLieuCoVua.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(DuLieuCoVua.NGUOI,viTri,huongDiMoi);
                if (viTriMoi == DuLieuCoVua.OTRONG || viTriMoi < 0){
                    if(vuaAnToan){
                        danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != DuLieuCoVua.OTRONG || !vuaAnToan) {                        
                            break;
                        }
                    }else if(viTriMoi != DuLieuCoVua.OTRONG) break;
                } else if(viTriMoi>0 && viTriMoi != DuLieuCoVua.OTRONG){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
    public void nuocDiMoiQuanTuong_May(QuanCo tuong){
        int viTri = tuong.viTriQuanCo;
        int[] huongDi = {11,-11,9,-9};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];            
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == DuLieuCoVua.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(DuLieuCoVua.MAY,viTri,huongDiMoi);
                if (viTriMoi == DuLieuCoVua.OTRONG || viTriMoi > 0) {
                    if(vuaAnToan){
                        danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != DuLieuCoVua.OTRONG || !vuaAnToan) {                        
                            break;
                        }
                    }else if(viTriMoi != DuLieuCoVua.OTRONG) break;
                } else if(viTriMoi<0){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
    public void nuocDiMoiQuanXe_Nguoi(QuanCo xe){
        int viTri = xe.viTriQuanCo;
        int[] huongDi = {1,-1,10,-10};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];            
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == DuLieuCoVua.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(DuLieuCoVua.NGUOI,viTri,huongDiMoi);
                if (viTriMoi == DuLieuCoVua.OTRONG || viTriMoi < 0) {
                    if(vuaAnToan){
                        danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != DuLieuCoVua.OTRONG) {          
                            break;
                        }
                    }else if(viTriMoi != DuLieuCoVua.OTRONG) break;
                } else if(viTriMoi>0 && viTriMoi != DuLieuCoVua.OTRONG){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
    public void nuocDiMoiQuanXe_May(QuanCo xe){
        int viTri = xe.viTriQuanCo;
        int[] huongDi = {1,-1,10,-10};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];           
            while (true) {
                 int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == DuLieuCoVua.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(DuLieuCoVua.MAY,viTri,huongDiMoi);
                if (viTriMoi == DuLieuCoVua.OTRONG || viTriMoi > 0) {
                    if(vuaAnToan){
                        danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != DuLieuCoVua.OTRONG) {                        
                            break;
                        }
                    }else if(viTriMoi != DuLieuCoVua.OTRONG) break;
                } else if(viTriMoi<0){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
    public void nuocDiMoiQuanHau_Nguoi(QuanCo hau){
        int viTri = hau.viTriQuanCo;
        int[] huongDi = {1,-1,10,-10,11,-11,9,-9};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];            
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == DuLieuCoVua.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(DuLieuCoVua.NGUOI,viTri,huongDiMoi);
                if (viTriMoi == DuLieuCoVua.OTRONG || viTriMoi < 0) {
                    if(vuaAnToan){
                        danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != DuLieuCoVua.OTRONG) {                        
                            break;
                        }
                    }else if(viTriMoi != DuLieuCoVua.OTRONG) break;
                } else if(viTriMoi>0 && viTriMoi != DuLieuCoVua.OTRONG){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
    public void nuocDiMoiQuanHau_May(QuanCo hau){
        int viTri = hau.viTriQuanCo;
        int[] huongDi = {1,-1,10,-10,11,-11,9,-9};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];            
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == DuLieuCoVua.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(DuLieuCoVua.MAY,viTri,huongDiMoi);
                if (viTriMoi == DuLieuCoVua.OTRONG || viTriMoi > 0) {
                    if(vuaAnToan){
                        danhSachVitri.add(new ViTri(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != DuLieuCoVua.OTRONG) {                        
                            break;
                        }
                    }else if(viTriMoi != DuLieuCoVua.OTRONG) break;
                } else if(viTriMoi<0){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
}
