import java.util.*;
class Main{
    static Admin admin = new Admin();
    static User user = new User();
    public static void main(String args[]){
        main0();
    }
    static void main0(){
        Scanner sc = new Scanner(System.in);
        while(true){
            flusher();
            System.out.print("-------*Welcome to Railway booking System*-------" +"\n" +"1.Admin" +"\n" +"2.User" +"\n" +"3.Exit" +"\n" +"Enter the Choice : ");
            String st= sc.nextLine();
            if(st.equals("1")){
                admin.admlog();
            }
            else if(st.equals("2")){
                user.usrlog();
            }
            else if(st.equals("3")){
                System.out.println("\n" +"\n" +"-------*Enter to Logout*-------");
                enterNeeded();
                System.exit(0);
            }
            else{
                System.out.println("Invalid Input");
                enterNeeded();
                main0();
            }
        }
    }
    //--------------------------------------------------------------------------------------Flushing Operation
    static void flusher(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    //--------------------------------------------------------------------------------------Enter Giver
    static void enterNeeded(){
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
    }
}
class Admin{
    //--------------------------------------------------------------------------------------Objects of Other Classes
    static Main main = new Main();
    static User user = new User();
    //--------------------------------------------------------------------------------------Admin Lists of Train
    static List<String> trainName = new ArrayList<String>();
    static List<List<String>> trainRoutes = new ArrayList<List<String>>();
    static HashMap<String,Integer> trainHp = new HashMap<String,Integer>(); 
    static List<int[][]> Bookings = new ArrayList<int[][]>();
    static List<List<String[]>> waitList = new ArrayList<List<String[]>>();
    //--------------------------------------------------------------------------------------Admin loggin
    static void admlog(){
        if(trainName.size()==0){
            List<String> lis = new ArrayList<String>();
            List<String[]> li = new ArrayList<String[]>();
            String[] ar = {"0"};
            li.add(ar);
            waitList.add(li);
            lis.add("0");
            int[][] arr= new int[0][0];
            trainName.add("0");
            trainRoutes.add(lis);
            Bookings.add(arr);
            trainHp.put("null",0);
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("-------------------*Welcome Admin*-------------------" +"\n" +"Enter the Admin Username : ");
        String username00 = sc.nextLine();
        System.out.print("Enter the Admin Password : ");
        String pwd = sc.nextLine(); 
        if((username00.equals("11")) && (pwd.equals("11"))){
            admActions(username00);
        }
        else{
            System.out.print("Invalid Input" +"\n" +"\n" +"Enter to Retry");
            main.enterNeeded();
            main.main0();
        }
        
    }
    //--------------------------------------------------------------------------------------Admin Actions
    static void admActions(String username00){
        main.flusher();
        Scanner sc = new Scanner(System.in);
        System.out.print("-------------------*Welcomeback Admin*----------------" +"\n" +"1. Create a Train Route" +"\n" +"2. View Available Trains" 
        +"\n" +"3. View Bookings" +"\n" +"4. Exit" +"\n" +"Enter the Choice : ");
        String adcho = sc.nextLine();
        if(adcho.equals("1")){
            createTrain(username00);//----------------------------------------------------------Complete
        }
        else if(adcho.equals("2")){
            viewTrains(username00);//-----------------------------------------------------------Complete
        }
        else if(adcho.equals("3")){
            for(int i = 1 ; i<trainName.size() ; i++){
                System.out.println(i +". " +trainName.get(i));
            }
            System.out.println("Enter the Choice to View Bookings : ");
            int a = sc.nextInt();
            viewBookings(username00,a);//--------------------------------------------------------Complete
        }
        else if(adcho.equals("4")){
                System.out.println("\n" +"\n" +"Enter to go to Login Page");
                main.enterNeeded();
                main.main0();
        }
        else{
            System.out.println("Invalid Input");
            main.enterNeeded();
            admActions(username00);
        }
    }
    //--------------------------------------------------------------------------------------Creating Train
    static void createTrain(String username00){
        main.flusher();
        Scanner sc = new Scanner(System.in);
        System.out.print("---------*Welcome Admin*---------" +"\n" +"Enter the Name of the Train : ");
        String tname = sc.nextLine();
        trainName.add(tname);
        System.out.println("Enter the Number of Places the Train going to Cover : ");
        int ro = sc.nextInt();
        System.out.println("Enter the Number of Seats you are going to Avail : ");
        int seat = sc.nextInt();
        sc.nextLine();
        List<String> lis = new ArrayList<String>();
        for(int i =0 ; i<ro ; i++){
            System.out.print("Enter the Route Names : ");
            String ro1 = sc.nextLine();
            lis.add(ro1);
        }
        List<String[]> liii = new ArrayList<String[]>();
        String[] arrrr={"0"};
        liii.add(arrrr);
        waitList.add(liii);
        trainRoutes.add(lis);
        int[][] ar = new int[ro][seat];
        Bookings.add(ar);
        trainHp.put(tname,trainName.size()-1);
        System.out.println("------------------*Train Added Successfully*-----------------" +"\n" +"Enter to Exit");
        main.enterNeeded();
        admActions(username00);
    }
    //-------------------------------------------------------------------------------------View Available Trains
    static void viewTrains(String username00){
        if(trainName.size()<=1){
            System.out.print("There are no Trains Available Now" +"\n" +"Enter to exit");
            main.enterNeeded();
            goBack(username00);
        }
        else{
        Scanner sc = new Scanner(System.in);
        List<String> lis = new ArrayList<String>();
        main.flusher();
        System.out.println("---------*Welcome " +username00 +"*---------");
        for(int i=1 ; i<trainName.size() ; i++){
            System.out.println(i +". " +trainName.get(i));
        }
        System.out.print("Enter the Train Number to See the Routes : ");
        int trnum = sc.nextInt();
        if(trainName.size()<trnum){
            System.out.print("Invalid Input" +"\n" +"Enter to Retry");
            main.enterNeeded();
            goBack(username00);
        }
        main.flusher();
        System.out.println("---------*Welcome " +username00 +"*---------");
        System.out.println("The Routes of " +trainName.get(trnum));
        lis=trainRoutes.get(trnum);
        for(int i=0 ; i<lis.size() ; i++){
            System.out.println(i+1 +". " +lis.get(i));
        }
        System.out.print("Enter to Exit");
        main.enterNeeded();
        goBack(username00);
        }
    }
    //-----------------------------------------------------------------------------------Going Back
    static void goBack(String username00){
        if(username00.equals("11")){
            admActions(username00);
        }
        else{
            user.usrActions(user.usrHp.get(username00),username00);
        }
    }
    //------------------------------------------------------------------------------------View Booked Trains
    static void viewBookings(String username00,int a){
        if(trainName.size()<=1){
            System.out.print("There are no Trains Available Now" +"\n" +"Enter to exit");
            main.enterNeeded();
            goBack(username00);
        }
        else{
        List<String> li = new ArrayList<String>();
        li = trainRoutes.get(a);
        int[][] arr = Bookings.get(a);
        for(int i = 0 ; i<arr.length ; i++){
            System.out.println("--------------*" +li.get(i) +"*--------------");
            for(int j =0;j<arr[0].length ; j++){
                System.out.print(arr[i][j] +" ");
            }
            System.out.println();
        }
        System.out.print("Enter to Exit");
        main.enterNeeded();
        goBack(username00);
        }
    }
    //------------------------------------------------------------------------------------Waiting List Filler
    static void waitFiller(int a){
        List<String[]> li = waitList.get(a);
        for(int i = 1 ; i<li.size() ; i++){
            String[] st = li.get(i);
            int a0 = Integer.parseInt(st[0]);
            String username00 = st[1];
            int[] arr= new int[3];
            arr[0]=Integer.parseInt(st[2]);
            arr[1]=Integer.parseInt(st[3]);
            arr[2]=Integer.parseInt(st[4]);
            user.bookTrains(a0,username00,arr);
        }
    }
}
class User{
    //------------------------------------------------------------------------------------Object Declaration
    static Main main = new Main ();
    static Admin admin = new Admin ();
    //------------------------------------------------------------------------------------User Lists
    static List<String> userName = new ArrayList<String>();
    static List<String> userPwd = new ArrayList<String>();
    static List<List<String>> mySeats = new ArrayList<List<String>>();
    static HashMap<String,Integer> usrHp = new HashMap<String,Integer>();
    //-------------------------------------------------------------------------------------User Login
    static void usrlog(){
        if(userName.size()==0){
            List<String> li = new ArrayList<String>();
            userName.add("0");
            userPwd.add("0");
            li.add("0");
            mySeats.add(li);
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("----------------*Welcome User*----------------" +"\n" +"1. New User" +"\n" +"2. Existing User" +"\n" +"3. Exit" 
        +"\n" +"Enter the Choice : ");
        String usch = sc.nextLine();
        if(usch.equals("1")){
            newLog();
        }
        else if(usch.equals("2")){
            exilog();
        }
        else if(usch.equals("3")){
            main.main0();
        }
        else{
            System.out.print("Invalid Input" +"\n" +"\n" +"Enter to Retry");
            main.enterNeeded();
            main.main0();
        }
    }
    //---------------------------------------------------------------------------------New User Registration
    static void newLog(){
        main.flusher();
        List<String> lis = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        System.out.print("--------------------*Welcome to Railway Booking System*------------------" +"\n" +"Enter the Username : ");
        String uname = sc.nextLine();
        System.out.print("Enter the Password : ");
        String Pwd = sc.nextLine();
        userName.add(userName.size(),uname);
        userPwd.add(userPwd.size(),Pwd);
        usrHp.put(uname,userName.size()-1);
        lis.add("0");
        mySeats.add(lis);
        System.out.println("----------------*Your Account is Successfully Registered*--------------");
        main.enterNeeded();
        usrActions(userName.size()-1,uname);
    }
    //------------------------------------------------------------------------------------Existing User Login
    static void exilog(){
        main.flusher();
        Scanner sc = new Scanner(System.in);
        System.out.print("--------------------*Welcome to Railway Booking System*------------------" +"\n" +"Enter the Username : ");
        String uname = sc.nextLine();
        System.out.print("Enter the Password : ");
        String Pwd = sc.nextLine();
        boolean t = userName.contains(uname);
        if(t){
            int ind = usrHp.get(uname);
            if(userPwd.get(ind).equals(Pwd)){
                usrActions(ind,uname);
            }
            else{
                System.out.println("\n" +"Invalid Password" +"\n" +"Enter to Retry");
                main.enterNeeded();
                usrlog();
            }
        }
        else{
            System.out.println("User not Exists" +"\n" +"Enter to Retry");
            main.enterNeeded();
            usrlog();
        }
    }
    //----------------------------------------------------------------------------------User Actions
    static void usrActions(int a , String username00){
        main.flusher();
        Scanner sc = new Scanner(System.in);
        System.out.print("-------------------*Welcome User*----------------" +"\n" +"1. See Trains & Routes" +"\n" +"2. Seat Booking" 
        +"\n" +"3. Cancel Bookings" +"\n" +"4. My Tickets and Seats " +"\n" +"5. Exit" +"\n" +"Enter the Choice : ");
        String adcho = sc.nextLine();
        if(adcho.equals("1")){
            admin.viewTrains(username00);//--------------------------------------------------------Complete
        }
        else if(adcho.equals("2")){
            int[] arr=viewTrain(a,username00);
            bookTrains(a,username00,arr);//--------------------------------------------------------Complete
            System.out.print("Enter to Exit");
            main.enterNeeded();
            usrActions(a,username00);
        }
        else if(adcho.equals("3")){
            cancelTickets(a,username00);//--------------------------------------------------------Complete
        }
        else if(adcho.equals("4")){
            viewBookings(a,username00);//--------------------------------------------------------Complete
            main.enterNeeded();
            usrActions(a,username00);
        }
        else if(adcho.equals("5")){
            System.out.println("\n" +"\n" +"Enter to go to Login Page");
            main.enterNeeded();
            main.main0();
        }
        else{
            System.out.println("Invalid Input");
            main.enterNeeded();
            usrActions(a,username00);
        }
    }
    //----------------------------------------------------------------------------------Booking process
    static void bookTrains(int a,String username00,int[] arr){
        int[][] ar = admin.Bookings.get(arr[0]);
        int b = arr[1];
        int d = arr[2];
        int count=0,ex=0;
        for(int j = 0 ; j<ar[0].length ; j++){
            if(ex==1){
                break;
            }
            else{
            for(int i=b ; i<=d ; i++){
                if(ar[i][j]==0){
                    count=count+1;
                    if(i==(d-1)){
                        System.out.println("The Seat Alloted for you is : " +(j+1));
                        ex=1;
                        for(int k=b ; k<=d ; k++){
                            ar[k][j] = a ;
                        }
                        List<String> li = new ArrayList<String>();
                        li=mySeats.get(a);
                        li.set(0,"Train Name : ");
                        li.add(admin.trainName.get(arr[0]));
                        li.add("My Seat is : ");
                        li.add(Integer.toString(j+1));
                        li.add("Boarding point : ");
                        li.add(Integer.toString(b+1));
                        li.add("Destination point : ");
                        li.add(Integer.toString(d+1));
                        break;
                    }
                }
                else{
                    break;
                }
            }
            }
        }
        if(ex==0){
            int cc = arr[0];
            List<String[]> liii = new ArrayList<String[]>();
            liii = admin.waitList.get(cc);
            if(liii.size()>=6){
                System.out.println("The Seats are Full" +"\n" +"Waiting List is Full");
            }
            else{
                String[] arrr = new String[5];
                arrr[0]=Integer.toString(a);
                arrr[1]=username00;
                arrr[2]=Integer.toString(arr[0]);
                arrr[3]=Integer.toString(arr[1]);
                arrr[4]=Integer.toString(arr[2]);
                liii.add(arrr);
                System.out.println("The Seats are Full" +"\n" +"Your Booking is in Waiting List");
            }
        }
    }
    //----------------------------------------------------------------------------------Geting Data for Booking
    static int[] viewTrain(int a,String username00){
        int[] arr = new int[3];
        if(admin.trainName.size()<=1){
            System.out.print("There are no Trains Available Now" +"\n" +"Enter to exit");
            main.enterNeeded();
            usrActions(a,username00);
        }
        else{
        Scanner sc = new Scanner(System.in);
        List<String> lis = new ArrayList<String>();
        main.flusher();
        System.out.println("---------*Welcome " +username00 +"*---------");
        for(int i=1 ; i<admin.trainName.size() ; i++){
            System.out.println(i +". " +admin.trainName.get(i));
        }
        System.out.print("Enter the Train Number to you want to Book : ");
        int trnum = sc.nextInt();
        arr[0]=trnum;
        if(admin.trainName.size()<trnum){
            System.out.print("Invalid Input" +"\n" +"Enter to Retry");
            main.enterNeeded();
            usrActions(a,username00);
        }
        main.flusher();
        System.out.println("---------*Welcome " +username00 +"*---------");
        System.out.println("The Routes of " +admin.trainName.get(trnum));
        lis=admin.trainRoutes.get(trnum);
        for(int i=0 ; i<lis.size() ; i++){
            System.out.println(i+1 +". " +lis.get(i));
        }
        System.out.print("Enter to Bording Point : ");
        int board = sc.nextInt();
        System.out.print("Enter to Destination Point : ");
        int des = sc.nextInt();
        arr[1]=board-1;
        arr[2]=des-1;
        }
        return arr;
    }
   //----------------------------------------------------------------------------------Viewing Each person Bookings 
    static int viewBookings(int a , String username00){
        List<String> li = mySeats.get(a);
        int trnum = 1;
        if(li.size()==0){
            System.out.println("Your Bookings are Empty");
        }
        else{
        for(int i = 0 ; i<li.size() ; i=i+8){
            System.out.println("----------------------------------------------");
            System.out.println("-----------------*" +trnum +"*-----------------");
            for(int j = i ; j<i+8 ; j=j+2){
            System.out.println(li.get(j) +li.get(j+1));
            }trnum++;
        }
        }
        return trnum;
    }
    //---------------------------------------------------------------------------------Canceling Tickets
    static void cancelTickets(int a , String username00){
        Scanner sc  =new Scanner(System.in);
        main.flusher();
        System.out.println("Enter the Booking Slot to Cancel that Ticket ");
        int trnum = viewBookings(a,username00);
        System.out.print("Enter the slot number to cancel : ");
        int a0 = sc.nextInt();
        if(a0>=trnum){
            System.out.println("Invalid Input" +"\n" +"Enter to Retry");
            main.enterNeeded();
            cancelTickets(a,username00);
        }
        else{
        List<String> li = mySeats.get(a);
        int r=(a0-1)*8;
        int sea = Integer.parseInt(li.get(r+3));
        int boa = Integer.parseInt(li.get(r+5));
        int des = Integer.parseInt(li.get(r+7));
        int tr = admin.trainName.indexOf(li.get(r+1));
        int[][] ar = admin.Bookings.get(tr);
        for(int k=boa-1 ; k<=des-1 ; k++){
            ar[k][sea-1] = 0 ;
        }
        admin.waitFiller(tr);
        li.remove(r);
        li.remove(r);
        li.remove(r);
        li.remove(r);
        li.remove(r);
        li.remove(r);
        li.remove(r);
        li.remove(r);
        System.out.println("Tickets Cancelled Successfully" +"\n" +"Enter to Exit");
        main.enterNeeded();
        usrActions(a,username00);
        }
    }
    
    
}
