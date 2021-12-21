package model;

public class QuanCo {
    public final static int TOT = 100;
    public final static int MA = 320;
    public final static int TUONG = 325;
    public final static int XE = 500;
    public final static int HAU = 900;
    public final static int VUA = 1000000;       
    public  int diem;
    public  int viTriQuanCo;
    public boolean dcPhepDiChuyen;
    public QuanCo(int diem,int viTriQuanCo){
        this(diem,viTriQuanCo,false);
    }
    public QuanCo(int diem,int viTriQuanCo,boolean dcPhepDiChuyen){
        this.diem = diem;
        this.viTriQuanCo = viTriQuanCo;
        this.dcPhepDiChuyen = dcPhepDiChuyen;
    }
    @Override
    public QuanCo clone(){
        return new QuanCo(diem,viTriQuanCo,dcPhepDiChuyen);
    }
}
