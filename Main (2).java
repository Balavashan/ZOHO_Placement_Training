import java.text.SimpleDateFormat;
import java.util.*;
class Booked{
    Date pickup;
    Date drop;
    String user;
}
class Car{
    int id;
    int nos;
    ArrayList<Booked> timeline = new ArrayList<>();
    int pph;
}

class Admin{
    String name;
    String pin;

}

class Bill{
    Date pickup;
    Date drop;
    int id;
    int amt;
}

class User{
    String name;
    String pin;
    ArrayList<Bill> History = new ArrayList<>();
}

class Main{
    public static long avail_date(Car x,String spickup,String sdrop) throws Exception{
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date pickup = f.parse(spickup);
        Date drop = f.parse(sdrop);
        long res = -1;
        Boolean y = false;
        if(x.timeline.size()!=0){
        for(int i=0;i<x.timeline.size();i++){
            if(pickup.before(x.timeline.get(i).pickup)&&drop.before(x.timeline.get(i).pickup)||pickup.after(x.timeline.get(i).drop)&&drop.after(x.timeline.get(i).drop)){
                y=true;
            }
        }
    }
    else{
        res=x.pph*(drop.getTime()-pickup.getTime())/3600000;
    }
    if(y){
        res=x.pph*(drop.getTime()-pickup.getTime())/3600000;
    }
        return res;
    }

    public static ArrayList<Integer> editcar(ArrayList<Car> x){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> n = new ArrayList<>();
        for(int i=0;i<x.size();i++){
            System.out.println(i+"-"+x.get(i).id);
        }
        System.out.println("Choose the car to edit : ");
        int ch = sc.nextInt();
        if(ch>=0 && ch<x.size()){
            System.out.println("0-Number Of Seats");
            System.out.println("1-Cost Per Hour");
            System.out.println("Choose The option You Should Edit : ");
            n.add(ch);
            int opt = sc.nextInt();
            if(opt<2&&opt>-1){
                System.out.println("Enter what it needs to be : ");
                int val = sc.nextInt();
                n.add(opt);
                n.add(val);
            }
            else{
                n.add(-1);
            }
        }
        else{
            n.add(-1);
        }
        return n;
    }
    public static int valid_user(ArrayList<User> x){
        Scanner sc = new Scanner(System.in);
        int res=-1;
        System.out.println("Enter your name : ");
        String name = sc.next();
        System.out.println("Enter your pin : ");
        String pin = sc.next();
        for(int i=0;i<x.size();i++){
            if(x.get(i).name.equals(name)&&x.get(i).pin.equals(pin)){
                res =i;
            }
        }
        return res;
    }
    public static int valid_admin(ArrayList<Admin> x){
        Scanner sc = new Scanner(System.in);
        int res=-1;
        System.out.println("Enter your name : ");
        String name = sc.next();
        System.out.println("Enter your pin : ");
        String pin = sc.next();
        for(int i=0;i<x.size();i++){
            if(x.get(i).name.equals(name)&&x.get(i).pin.equals(pin)){
                res =i;
            }
        }
        return res;
    }
    public static User new_User(){
        Scanner sc = new Scanner(System.in);
        User n = new User();
        System.out.println("Enter Your Name : ");
        String uname = sc.next();

        System.out.println("Enter Your pin : ");
        String pin = sc.next();
        n.name =uname;
        n.pin = pin;
        return n;
    }
    public static Car addcar(ArrayList<Car> x){
        Scanner sc = new Scanner(System.in);
        Car c = new Car();
        System.out.println("Enter Car ID : ");
        int id = sc.nextInt();
        Boolean ch = true;
        for(int i=0;i<x.size();i++){
            if(x.get(i).id==id){
                ch=false;
            }
        }
        System.out.println("Enter No. of Seats : ");
        int nos = sc.nextInt();
        System.out.println("Enter Cost Per Hour : ");
        int pph = sc.nextInt();
        c.nos = nos;
        c.pph = pph;
        if(ch){
            c.id = id;
        }
        else{
            c.id=-1;
        }
        return c;
    }
    public static int delcar(ArrayList<Car> x){
        Scanner sc = new Scanner(System.in);
        int res=-1;
        for(int i=0;i<x.size();i++){
            System.out.println(i+"- Car ID :: "+x.get(i).id);
        }
        int val = sc.nextInt();
        if(val<x.size()&&val>0){
            res=val;
        }
        return res;
    }
    public static void main(String[] args) throws Exception {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Scanner sc = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        User u1 = new User();
        User u2 = new User();
        u1.name = "balu";
        u1.pin = "v123";
        u2.name = "hari";
        u2.pin = "u456";
        users.add(u1);
        users.add(u2);
        Car c1 = new Car();
        Car c2 = new Car();
        c1.id=1;
        c2.id=2;
        c1.nos = 5;
        c2.nos = 8;
        c1.pph = 500;
        c2.pph = 800;
        cars.add(c1);
        cars.add(c2);
        Admin a1 = new Admin();
        a1.name = "akshay";
        a1.pin = "b1b1"; 
        admins.add(a1);
        Boolean whole_exit = false;
        while(!whole_exit){
        System.out.println("1-Admin");
        System.out.println("2-User");
        System.out.println("3-Exit");
        System.out.println("Enter Your Choice : ");
        int ch = sc.nextInt();
        switch(ch){
            case 1:int al = valid_admin(admins);
            if(al!=-1){
                Boolean admin_exit = false;
                while(!admin_exit){
                System.out.println("1-Add Cars");
                System.out.println("2-Delete Cars");
                System.out.println("3-Edit Car Details");
                System.out.println("4-View Booked Cars");
                System.out.println("5-View All Cars");
                System.out.println("6-LogOut");
                System.out.println("Enter Your Choice : ");
                int ach = sc.nextInt();
                switch(ach){
                    case 1: Car k = addcar(cars);
                    System.out.println("Car Added Successfully");
                    if(k.id!=-1){
                        cars.add(k);
                    }
                    else{
                        System.out.println("ID is not available");
                    }
                    break;
                    case 2:int dc = delcar(cars);
                    if(dc!=-1){
                        cars.remove(dc);
                    }
                    else{
                        System.out.println("Select a valid number");
                    }
                    break;
                    case 3:ArrayList<Integer> edcar = editcar(cars);
                    if(edcar.get(0)!=-1){
                        if(edcar.get(1)!=-1){
                            if(edcar.get(1)==0){
                                cars.get(edcar.get(0)).nos=edcar.get(2);
                            }
                            else{
                                cars.get(edcar.get(0)).pph=edcar.get(2);
                            }
                        }
                        else{
                            System.out.println("Enter a valid Option");
                        }
                    }
                    else{
                        System.out.println("Enter a valid option");
                    }
                    break;
                    case 4:for(int i =0;i<cars.size();i++){
                        System.out.println("Car ID : "+cars.get(i).id);
                        for(int j=0;j<cars.get(i).timeline.size();j++){
                            System.out.println("Booked By : "+cars.get(i).timeline.get(j).user);
                            System.out.println("Pickup time : "+cars.get(i).timeline.get(j).pickup);
                            System.out.println("Drop Time : "+cars.get(i).timeline.get(j).drop);
                            System.out.println("----------------------------------------");
                        }
                        System.out.println("----------------------------------------");
                    }
                    break;
                    case 5:for(int i=0;i<cars.size();i++){
                        System.out.println("car id : "+cars.get(i).id);
                        System.out.println("Avail. Seats : "+cars.get(i).nos);
                        System.out.println("Cost Per Hour : "+cars.get(i).pph);
                        System.out.println("----------------------");
                    }
                    break;
                    case 6:admin_exit = true;
                    break;
                    default:System.out.println("Enter a valid Option");
                }
                }
            }
            else{
                System.out.println("Enter  credentials doesnot match");
            }
            break;
            case 2:System.out.println("1-New User");
                System.out.println("2-Existing User");
                System.out.println("3-Exit");
                System.out.println("Enter Your Choice : ");
                int url = sc.nextInt();
                switch(url){
                    case 1:users.add(new_User());
                    break;
                    case 2:int ul = valid_user(users);
                    if(ul!=-1){
                        Boolean user_exit=false;
                        while(!user_exit){
                            System.out.println("1-Book a car");
                            System.out.println("2-Cancel Booking");
                            System.out.println("3-View Booked Cars");
                            System.out.println("4-logout");
                            System.out.println("Enter Your Choice : ");
                            int uch = sc.nextInt();
                            switch(uch){
                            case 1:for(int i=0;i<cars.size();i++){
                                System.out.println("car id : "+cars.get(i).id);
                                System.out.println("Avail. Seats : "+cars.get(i).nos);
                                System.out.println("Cost Per Hour : "+cars.get(i).pph);
                                System.out.println("----------------------");
                            }
                            System.out.println("Enter The car ID You want to book : ");
                            int bcID = sc.nextInt();
                            int indice=-1;
                            for(int i=0;i<cars.size();i++){
                                if(cars.get(i).id==bcID){
                                    indice=i;
                                }
                            }
                            if(indice!=-1){
                                System.out.println("Enter the Day You Want to Pickup the car : in (dd-mm-yyyy)");
                                String pdate  = sc.next();
                                System.out.println("Enter the time You Want to Pickup the car : in (HH:MM)");
                                String ptime  = sc.next();
                                String ppickup =pdate+" "+ptime;
                                System.out.println("Enter the Day You Want to drop the car : in (dd-mm-yyyy)");
                                String ddate  = sc.next();
                                System.out.println("Enter the time You Want to drop the car : in (HH:MM)");
                                String dtime  = sc.next();
                                String ddrop = ddate+" "+dtime;
                                long ad = avail_date(cars.get(indice),ppickup,ddrop);
                                if(ad!=-1){
                                    System.out.println("Total Amount is : "+ad);
                                    System.out.println("Do You Wish to book ?");
                                    System.out.println("Press 1 to confirm");
                                    int b = sc.nextInt();
                                    if(b==1){
                                    Date pickup = f.parse(ppickup);
                                    Date drop = f.parse(ddrop);
                                    Booked bk = new Booked();
                                    bk.pickup = pickup;
                                    bk.drop  = drop;
                                    bk.user = users.get(ul).name;
                                    cars.get(indice).timeline.add(bk);
                                    Bill bill = new Bill();
                                    bill.amt = (int)ad;
                                    bill.pickup = pickup;
                                    bill.drop = drop;
                                    bill.id = cars.get(indice).id;
                                    users.get(ul).History.add(bill);
                                    System.out.println("Booked Successfully");
                                    }
                                    else{
                                        System.out.println("Booking Cancelled By User");
                                    }
                                }else{
                                    System.out.println("Car Not Available");
                                }
                            }
                            else{
                                System.out.println("Incorrect ID Entered");
                            }
                            break;
                            case 2:for(int i=0;i<users.get(ul).History.size();i++){
                                System.out.println("Car ID : "+users.get(ul).History.get(i).id);
                                System.out.println("Pickup Time : "+users.get(ul).History.get(i).pickup);
                                System.out.println("Drop Time : "+users.get(ul).History.get(i).drop);
                                System.out.println("Total Amount : "+users.get(ul).History.get(i).amt);
                            }
                            System.out.println("Enter The car ID You want to book : ");
                            int caID = sc.nextInt();
                            int indi=-1;
                            for(int i=0;i<cars.size();i++){
                                if(cars.get(i).id==caID){
                                    indi=i;
                                }
                            }
                            int tindi=-1;
                            for(int i=0;i<cars.get(indi).timeline.size();i++){
                                if(cars.get(indi).timeline.get(i).user==users.get(ul).name){
                                    tindi=i;
                                }
                            }
                            int Hindi =-1;
                            for(int i=0;i<users.get(ul).History.size();i++){
                                if(users.get(ul).History.get(i).id==caID){
                                    Hindi = i;
                                }
                            }
                            users.get(ul).History.remove(Hindi);
                            cars.get(indi).timeline.remove(tindi);


                            break;
                            case 3:for(int i=0;i<users.get(ul).History.size();i++){
                                System.out.println("Car ID : "+users.get(ul).History.get(i).id);
                                System.out.println("Pickup Time : "+users.get(ul).History.get(i).pickup);
                                System.out.println("Drop Time : "+users.get(ul).History.get(i).drop);
                                System.out.println("Total Amount : "+users.get(ul).History.get(i).amt);
                            }
                            break;
                            case 4:user_exit=true;
                            break;
                            default:System.out.println("Choose a valid Option");
                            }
                        }
                    }System.out.println("Entered Credentials Doesnot Match");
                }
                break;
                case 3:whole_exit=true;
                break;
                default:System.out.println("Enter a valid Option");
        }
        }
        System.out.println("Thank You for Your Time");
    }
} 