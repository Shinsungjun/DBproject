package kau.db;
public class DataOfEmployee {
    // DATA 저장 방식은 ArrayList로 변경해보자
    private String name,ssn,Bdate,address,sex,supermgr_name,dname;
    private Double salary;
    public DataOfEmployee(String name, String ssn,String Bdate,String address,String sex,String supermgr_name,String dname,Double salary ){
        this.name = name ;
        this.ssn = ssn;
        this.Bdate = Bdate;
        this.address = address;
        this.sex = sex;
        this.supermgr_name = supermgr_name;
        this.dname = dname;
        this.salary = salary;
    }
    public String toString(){
        return name+" "+ssn+" "+Bdate+" "+address+" "+sex+" "+" "+salary+" "+supermgr_name+" "+dname;
    }
    public String getSsn() {
        return ssn;
    }
    public String getDname() {
        return dname;
    }
    public String getName(){ return name; }
    public String getBdate(){ return Bdate; }
    public String getAddress(){return address;}
    public String getSex(){return sex;}
    public String getSupermgr_name(){ return supermgr_name;}
    public Double getSalary(){ return salary;}
}
