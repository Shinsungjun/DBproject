package kau.db;

import java.util.ArrayList;

public class DataOfEmployee {
    // DATA 저장 방식은 ArrayList로 변경해보자
    private ArrayList<String> data;
    public DataOfEmployee(String name, String ssn,String Bdate,String address,String sex,String supermgr_name,String dname,String salary ){
        data.add(name);
        data.add(ssn);
        data.add(Bdate);
        data.add(address);
        data.add(sex);
        data.add(supermgr_name);
        data.add(salary);
        data.add(dname);
    }
    public String toString(){
        return data.get(0)+" "+data.get(1)+" "+data.get(2)+" "+data.get(3)+" "+data.get(4) +
                " "+data.get(5)+" "+data.get(6)+" "+data.get(7);
    }
    public String getSsn() {
        return data.get(0);
    }
    public String getDname() {
        return data.get(1);
    }
    public String getName(){ return data.get(2); }
    public String getBdate(){ return data.get(3); }
    public String getAddress(){return data.get(4);}
    public String getSex(){return data.get(5);}
    public String getSupermgr_name(){ return data.get(6);}
    public String getSalary(){ return data.get(7);}
}
