package model;

/**
 *
 * @author nhat_IT
 */
public class ViTri {
	public  DiChuyen nuocDi;
	public  int[] banCo = new int[120];
    public  QuanCo[] cacLoaiQuanCoNguoi = new QuanCo[17];
    public  QuanCo[] cacLoaiQuanCoMay = new QuanCo[17];
    
    public ViTri(){
        for(int i=0; i<banCo.length; i++){
            banCo[i] = DuLieuCoVua.OTRONG;
        }
    }
    public ViTri(ViTri viTri){
        this(viTri,null);
    }
    @Override
	public String toString() {
		return "ViTri [nuocDi=" + nuocDi + "]";
	}
	public ViTri(ViTri viTri, DiChuyen nuocDi){
        System.arraycopy(viTri.banCo, 0, this.banCo, 0, banCo.length);
        for(int i=1; i<cacLoaiQuanCoNguoi.length; i++){
            if(viTri.cacLoaiQuanCoNguoi[i] != null){
                this.cacLoaiQuanCoNguoi[i] = viTri.cacLoaiQuanCoNguoi[i].clone();
            }
            if(viTri.cacLoaiQuanCoMay[i] != null){
                this.cacLoaiQuanCoMay[i] = viTri.cacLoaiQuanCoMay[i].clone();
            }
        }
    if(nuocDi != null) update(nuocDi);
    }    
    public void taoBanCo(boolean humanWhite){         
        cacLoaiQuanCoNguoi[1] = new QuanCo(QuanCo.MA,92);
        cacLoaiQuanCoNguoi[2] = new QuanCo(QuanCo.MA,97);
        cacLoaiQuanCoNguoi[3] = new QuanCo(QuanCo.TUONG,93);
        cacLoaiQuanCoNguoi[4] = new QuanCo(QuanCo.TUONG,96);
        cacLoaiQuanCoNguoi[5] = new QuanCo(QuanCo.XE,91);
        cacLoaiQuanCoNguoi[6] = new QuanCo(QuanCo.XE,98);
        cacLoaiQuanCoNguoi[7] = new QuanCo(QuanCo.HAU,humanWhite?94:95);
        cacLoaiQuanCoNguoi[8] = new QuanCo(QuanCo.VUA,humanWhite?95:94);
        
        cacLoaiQuanCoMay[1] = new QuanCo(QuanCo.MA,22);
        cacLoaiQuanCoMay[2] = new QuanCo(QuanCo.MA,27);
        cacLoaiQuanCoMay[3] = new QuanCo(QuanCo.TUONG,23);
        cacLoaiQuanCoMay[4] = new QuanCo(QuanCo.TUONG,26);
        cacLoaiQuanCoMay[5] = new QuanCo(QuanCo.XE,21);
        cacLoaiQuanCoMay[6] = new QuanCo(QuanCo.XE,28);
        cacLoaiQuanCoMay[7] = new QuanCo(QuanCo.HAU,humanWhite?24:25);
        cacLoaiQuanCoMay[8] = new QuanCo(QuanCo.VUA,humanWhite?25:24); 
        
        int j = 81;
        for(int i=9; i<cacLoaiQuanCoNguoi.length; i++){
            cacLoaiQuanCoNguoi[i] = new QuanCo(QuanCo.TOT,j);
            cacLoaiQuanCoMay[i] = new QuanCo(QuanCo.TOT,j-50);
            j++;
        }                      
        banCo = new int[]{
            DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.OTRONG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,
            DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG,DuLieuCoVua.KHUNG
        };        
        for(int i=0; i<banCo.length; i++){                        
            for(int k=1; k<cacLoaiQuanCoNguoi.length; k++){
                if(i==cacLoaiQuanCoNguoi[k].viTriQuanCo){
                    banCo[i] = k;
                }else if(i==cacLoaiQuanCoMay[k].viTriQuanCo){
                    banCo[i] = -k;
                }
            }
        }
    }    
    public void update(DiChuyen diChuyen){
        this.nuocDi = diChuyen;   
        int viTriDau = banCo[diChuyen.viTriDau];
        int viTriCuoi = banCo[diChuyen.viTriDich];  
        if(viTriDau>0){
            cacLoaiQuanCoNguoi[viTriDau].dcPhepDiChuyen = true;
            cacLoaiQuanCoNguoi[viTriDau].viTriQuanCo = diChuyen.viTriDich;
            if(viTriCuoi<0){                
                cacLoaiQuanCoMay[-viTriCuoi] = null;
            }            
        }else{
            cacLoaiQuanCoMay[-viTriDau].dcPhepDiChuyen = true;
            cacLoaiQuanCoMay[-viTriDau].viTriQuanCo = diChuyen.viTriDich;
            if(viTriCuoi>0 && viTriCuoi != DuLieuCoVua.OTRONG){                
                cacLoaiQuanCoNguoi[viTriCuoi] = null;
            }            
        }
        banCo[diChuyen.viTriDau] = DuLieuCoVua.OTRONG;
        banCo[diChuyen.viTriDich] = viTriDau;
    }
    public void displayBoard() {
       int j=0;
       int i=0;
       int k=10;
while (j<12){
        while (i<k){
        	 System.out.print(banCo[i]+"\t");
        	 i++;
        }System.out.println("\n");
        k+=10;
        j++;
          
//      int k = 0;
//      for(int i=0; i<12; i++){
//          for(int j=0; j<10; j++){
//              System.out.print(viTri.banCo[k]+" ");
//              k++;
//          }
//          System.out.println();                
//      }

        }
    } 
}
