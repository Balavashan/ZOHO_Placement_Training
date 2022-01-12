import java.util.*;

public class Amazon {
    static Scanner sc = new Scanner(System.in);
    static String Admin_Id = "Admin";
    static String Admin_Password = "1234";
    static int Admin_attempt = 3;
    static int Merchant_attempt = 3;
    static int User_Attempt = 3;
    static int M_Id = 1;
    static int U_Id = 1;
    static int Current_Merchant = -1;
    static int Current_User = -1;
    static Dictionary<String, ArrayList<String>> All_Products = new Hashtable<>();

    // ArrayList of object

    // For Merchant
    static ArrayList<Create_Merchant_obj> Merchants = new ArrayList<>();

    // For User
    static ArrayList<Creat_User_Obj> User_List = new ArrayList<>();

    // Clear Screen
    static void Clr_scr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Admin Process

    // Admin Login
    static void Admin_Login() {
        while (Admin_attempt >= 0) {
            System.out.print("Welcome to Admin Login \nEnter the Admin Id : ");
            String ID = sc.next();
            sc.nextLine();
            System.out.print("\nEnter the Admin Password : ");
            String Password = sc.next();
            sc.nextLine();
            if (ID.equals(Admin_Id) && Password.equals(Admin_Password)) {
                Admin();
            } else {
                Admin_attempt -= 1;
                System.out.println(
                        "You have entered the wrong Password!\nYou have only " + Admin_attempt + " Attempts :-)");
            }
        }
    }

    // Admin panel
    static void Admin() {
        while (true) {
            System.out.println(
                    "Welcome to Admin Panel\n1 => View all Products\n2 => Add new Merchent\n3 => Merchant's Approval\n4 => List of Merchents\n5 => Delete Merchant\n6 => Add new Product\n7 => Back");
            int Chooise = sc.nextInt();
            if (Chooise == 1) {
                Clr_scr();
                System.out.println(All_Products);
            } else if (Chooise == 2) {
                Add_Merchent();
            } else if (Chooise == 3) {
                System.out.println("Enter Merchant Id : ");
                String MerId = sc.next();
                sc.nextLine();
                for (Create_Merchant_obj merch : Merchants) {
                    if (MerId.equals(Integer.toString(merch.M_Id))) {
                        merch.M_Verified = "Yes";
                        System.out.println("==> Merchant Name : " + merch.M_Name + " Merchant Id : " + merch.M_Id
                                + " is Verified : " + merch.M_Verified);
                    } else {
                        System.out.println("User Id is not founded");
                    }
                }
            } else if (Chooise == 4) {
                List_Of_Merchents();
            } else if (Chooise == 5) {
                System.out.print("Enter the Id of the Merchant : ");
                String id = sc.next();
                sc.nextLine();
                int temp = -1;
                int i = 0;
                for (Create_Merchant_obj merch : Merchants) {
                    if ((Integer.toString(merch.M_Id)).equals(id)) {
                        temp = i;
                    }
                    i += 1;
                }
                if (temp != -1) {
                    Merchants.remove(temp);
                } else {
                    System.out.println("Id is not match with any other Id's :-)");
                }
            } else if (Chooise == 6) {
                System.out.print("Enter the Product Name : ");
                String P_Name = sc.next();
                sc.nextLine();
                List<String> arr = new ArrayList<>();
                arr.add("0");
                arr.add("0");
                Enumeration enu = All_Products.keys();
                int k = -1;
                while (enu.hasMoreElements()) {
                    if (P_Name.equals(enu.nextElement())) {
                        k = 1;
                    }
                }
                if (k == -1) {
                    All_Products.put(P_Name, (ArrayList<String>) arr);

                } else {
                    System.out.println("Product is already found in the List !");
                }
            } else if (Chooise == 7) {
                main(null);
            } else {
                System.out.println("Enter the valid Chooise !");
            }
        }
    }

    // Shows the list of merchants
    public static void List_Of_Merchents() {
        System.out.println("Merchants Names :");
        for (Create_Merchant_obj merch : Merchants) {
            System.out.println("==> Merchant Name : " + merch.M_Name + " Merchant Id : " + merch.M_Id
                    + " is Verified : " + merch.M_Verified);
        }
    }

    // Add the new Merchant
    public static void Add_Merchent() {
        System.out.print("Enter Merchent Name : ");
        String name = sc.next();
        sc.nextLine();
        System.out.print("Enter Merchent Password : ");
        String password = sc.next();
        sc.nextLine();
        Merchants.add(new Create_Merchant_obj(name, password, "Yes", M_Id));
        System.out.println("Your User Id => " + M_Id);
        M_Id += 1;
    }

    // Merchant Process

    // Merchant Login panel
    static void Merch() {
        System.out.println("Welcome to Merchant \n1 => For Login\n2 => For Regester\n3 => Back");
        int Ch = sc.nextInt();
        if (Ch == 1) {
            Mer_Login();
        } else if (Ch == 2) {
            Mer_Reg(Ch);
        } else if (Ch == 3) {
            main(null);
        } else {
            System.out.println("Enter the valid Chooise !");
        }
    }

    // Merchant Login
    static void Mer_Login() {
        while (Merchant_attempt >= 0 || true) {
            System.out.print("Welcome to Merchant Login \nEnter your Id : ");
            String ID = sc.next();
            sc.nextLine();
            System.out.print("\nEnter your Password : ");
            String Password = sc.next();
            sc.nextLine();
            int i = 0;
            for (Create_Merchant_obj merch : Merchants) {
                if (ID.equals(Integer.toString(merch.M_Id)) && Password.equals(merch.M_Password)) {
                    if (merch.M_Verified.equals("Yes")) {
                        Merchant_attempt = 3;
                        Current_Merchant = i;
                        Merchant();
                    } else {
                        System.out.println("User is not verified !");
                        Merch();
                    }
                }
                i += 1;
            }
            Merchant_attempt -= 1;
            Merch();
        }
    }

    // merchant Register
    static void Mer_Reg(int Ch) {
        System.out.println("\nMerchent Register \nEnter Your Name : ");
        String name = sc.next();
        sc.nextLine();
        System.out.println("Enter your Password : ");
        String password = sc.next();
        sc.nextLine();
        Merchants.add(new Create_Merchant_obj(name, password, "No", M_Id));
        System.out.println("Your User Id => " + M_Id);
        M_Id += 1;
        Merch();
    }

    // Merchant Panel
    static void Merchant() {
        while (true) {
            System.out.println(
                    "Welcome to Merchant Panal\n1 => Add Product\n2 => Update Product\n3 => Compare Product\n4 => Remove Product\n5 => List the Products\n6 => Back");
            int Ch = sc.nextInt();
            if (Ch == 1) {
                Add_Product(Current_Merchant);
            } else if (Ch == 2) {
                M_Update_Product();
            } else if (Ch == 3) {
                M_Comp_Product();
            } else if (Ch == 4) {
                M_Remove_Product();
            } else if (Ch == 5) {
                M_Show_List();
            } else if (Ch == 6) {
                main(null);
            } else {
                System.out.println("Enter the valid Input !");
            }
        }
    }

    // Compare Merchant's Based On their Products
    static void M_Comp_Product() {
        System.out.println("Enter the Product Name : ");
        String product = sc.next();
        sc.nextLine();
        System.out.println("Product Name : " + product);
        int k = 0;
        for (int i = 0; i < Merchants.size(); i++) {
            Enumeration enu = Merchants.get(i).M_Products.keys();
            while (enu.hasMoreElements()) {
                if (enu.nextElement().equals(product)) {
                    System.out.println("Merchant Name : " + Merchants.get(i).M_Name + "  Product Price : "
                            + Merchants.get(i).M_Products.get(product).get(1));
                    k += 1;
                }
            }
        }
        if (k <= 0) {
            System.out.println("Product is Not Found !");
        }
    }

    // Remove the Product In the Merchant's List
    static void M_Remove_Product() {
        System.out.print("Enter the product name : ");
        String product = sc.next();
        sc.nextLine();
        try {
            List<String> s = Merchants.get(Current_Merchant).M_Products.remove(product);
            System.out.println(s + "   " + Merchants.get(Current_Merchant).M_Products);
            List<String> arr = new ArrayList<>();
            int count1 = Integer.parseInt(All_Products.get(product).get(0)) - Integer.parseInt(s.get(0));
            String price = All_Products.get(product).get(1);
            arr.add(Integer.toString(count1));
            arr.add(price);
            All_Products.put(product, (ArrayList<String>) arr);
            System.out.println("Product removed Successfully !");
        } catch (Exception e) {
            System.out.println("Product is Not Found in your Product List !");
        }
    }

    // Update the Product
    static void M_Update_Product() {
        try {
            System.out.print("Enter the product name : ");
            String product = sc.next();
            sc.nextLine();
            // String g = Merchants.get(Current_Merchant).M_Products.get(product).get(0);
            if (!Merchants.get(Current_Merchant).M_Products.get(product).get(0).equals("null")) {

                System.out.println("Enter the product Count : ");
                int count = sc.nextInt();
                System.out.println("Enter the Updated Price : ");
                int price = sc.nextInt();
                List<String> arr1 = new ArrayList<>();
                // count += Integer.parseInt(g);
                String h = Merchants.get(Current_Merchant).M_Products.get(product).get(0);
                arr1.add(Integer.toString(count));
                arr1.add(Integer.toString(price));
                Merchants.get(Current_Merchant).M_Products.put(product, (ArrayList<String>) arr1);
                List<String> arr = new ArrayList<>();
                int count1 = (Integer.parseInt(All_Products.get(product).get(0))) - (Integer.parseInt(h) - count);
                arr.add(Integer.toString(count1));
                arr.add(Integer.toString(price));
                All_Products.put(product, (ArrayList<String>) arr);

            }
        } catch (Exception e) {
            System.out.println("The product is not found in the List !");
        }
    }

    // Add new Product In the Merchant's List
    static void Add_Product(int id) {
        System.out.println("Enter the Product Name : ");
        String product = sc.next();
        sc.nextLine();
        Enumeration enu = All_Products.keys();
        int k = -1;
        while (enu.hasMoreElements()) {
            if (product.equals(enu.nextElement())) {
                k = 1;
            }
        }
        if (k == -1) {
            System.out.println("Product is not found in the Admin's List !");
        } else {
            System.out.println("Enter the count of the product : ");
            int count = sc.nextInt();
            System.out.println("Enter the price for a Item : ");
            int price = sc.nextInt();
            int count1 = count + Integer.parseInt(All_Products.get(product).get(0));
            List<String> arr = new ArrayList<>();
            arr.add(Integer.toString(count1));
            arr.add(Integer.toString(price));
            All_Products.put(product, (ArrayList<String>) arr);
            List<String> arr1 = new ArrayList<>();
            String g = "0";
            try {
                g = Merchants.get(Current_Merchant).M_Products.get(product).get(0);
            } catch (Exception e) {
                System.out.println("");
            }
            count += Integer.parseInt(g);
            arr1.add(Integer.toString(count));
            arr1.add(Integer.toString(price));
            Merchants.get(Current_Merchant).M_Products.put(product, (ArrayList<String>) arr1);
        }
    }

    // List of Merchant's Products
    static void M_Show_List() {
        System.out.println(Merchants.get(Current_Merchant).M_Products);
    }

    // User Process

    // User login Panel
    static void User() {
        System.out.println("Welcome to User Panel :\n1 => Login\n2 => Register\n3 => Go Back");
        int Ch = sc.nextInt();
        User_Attempt = 3;
        if (Ch == 1) {
            U_Login();
        } else if (Ch == 2) {
            U_Register();
        } else if (Ch == 3) {
            main(null);
        } else {
            System.out.println("Enter the valid Chooise !");
        }
    }

    // User Login
    static void U_Login() {
        while (User_Attempt >= 0) {
            System.out.print("Login Page\nEnter the User User Id : ");
            String id = sc.next();
            sc.nextLine();
            System.out.print("Enter the Password : ");
            String password = sc.next();
            sc.nextLine();
            for (int i = 0; i < User_List.size(); i++) {
                // System.out.println(User_List.get(i).U_Id+" "+id+"
                // "+User_List.get(i).U_Password+" "+password);
                if (Integer.toString(User_List.get(i).U_Id).equals(id)
                        && User_List.get(i).U_Password.equals(password)) {
                    Current_User = i;
                    User_panel();
                    break;
                }
            }
            User_Attempt -= 1;
            User();
        }
    }

    // User Panel
    static void User_panel() {
        System.out.println("Hello " + User_List.get(Current_User).U_Name + " Welcome to user Panel : ");
        while (true) {
            System.out.println(
                    "1 => List of Products \n2 => Show Cart \n3 => Purchase History \n4 => Wallet \n5 => Exit ");
            int Ch = sc.nextInt();
            if (Ch == 1) {
                List_User_Products();
            } else if (Ch == 2) {
                User_Cart();
            } else if (Ch == 3) {
                System.out.println("Purchase History !");
                for (int k = User_List.get(Current_User).U_Buy.size() - 1; k >= 0; k--) {
                    System.out.println(User_List.get(Current_User).U_Buy.get(k));
                }
            } else if (Ch == 4) {
                Wallet();
            } else if (Ch == 5) {
                main(null);
            } else {
                System.out.println("Please Enter the valid Chooise !");
            }
        }
    }

    // List Of Products
    static void List_User_Products() {
        while (true) {
            Enumeration enu = All_Products.keys();
            while (enu.hasMoreElements()) {
                String pro = (String) enu.nextElement();
                System.out.println("==> Product Name : " + pro + " Product Count : "
                        + All_Products.get(pro).get(0));
            }
            System.out.println("1 => Add to Cart \n2 => Back");
            int Ch = sc.nextInt();
            if (Ch == 1) {
                Buy_User();
            } else if (Ch == 2) {
                User_panel();
            } else {
                System.out.println("Please Enter the valid Chooise !");
            }
        }
    }

    // Check the Product is available or not
    static void Buy_User() {
        System.out.print("Enter the Product Name : ");
        String Product = sc.next();
        sc.nextLine();
        // Enumeration enu = All_Products.keys();
        int k = -1;
        // while (enu.hasMoreElements()) {
        // if (Product.equals(enu.nextElement()) &&
        // Integer.parseInt(All_Products.get(Product).get(0)) > 0) {
        // k = 1;
        // }
        // }

        for (int i = 0; i < All_Products.size(); i++) {
            if (Integer.parseInt(All_Products.get(Product).get(0)) > 0) {
                k = i;
            }
        }
        if (k == -1) {
            System.out.println("Product Out Of Stock !");
        } else {
            Show_Product_List(Product);
        }
    }

    // Shows the List of Products And add the product to the cart
    static void Show_Product_List(String product) {
        int k = 0;
        for (int i = 0; i < Merchants.size(); i++) {
            Enumeration enu = Merchants.get(i).M_Products.keys();
            while (enu.hasMoreElements()) {
                if (enu.nextElement().equals(product)
                        && Integer.parseInt(Merchants.get(i).M_Products.get(product).get(0)) > 0) {
                    System.out.println("==> Merchant Name : " + Merchants.get(i).M_Name + " -- Merchant Id : "
                            + Merchants.get(i).M_Id + "  --Product Count : "
                            + Merchants.get(i).M_Products.get(product).get(0) + "  --Product Price : "
                            + Merchants.get(i).M_Products.get(product).get(1));
                    k += 1;
                }
            }
        }
        if (k <= 0) {
            System.out.println("Product is Not Found !");
        } else {

            System.out.print("Enter the Merchant Id :");
            String M_Id = sc.next();
            sc.nextLine();
            int not = 0;

            for (int i = 0; i < Merchants.size(); i++) {

                if (Integer.toString(Merchants.get(i).M_Id).equals(M_Id)) {

                    System.out.println(M_Id + " -- " + product);
                    System.out.print("Enter the Product Count : ");
                    int count = sc.nextInt();

                    if (Integer.parseInt(Merchants.get(i).M_Products.get(product).get(0)) >= count) {

                        List<String> arr = new ArrayList<>();

                        arr.add(Integer
                                .toString(Integer.parseInt(Merchants.get(i).M_Products.get(product).get(0)) - count));
                        arr.add(Merchants.get(i).M_Products.get(product).get(1));

                        // Update the merchant's List
                        Merchants.get(i).M_Products.put(product, (ArrayList<String>) arr);

                        List<String> arr1 = new ArrayList<>();

                        arr1.add(Integer.toString(Integer.parseInt(All_Products.get(product).get(0)) - count));
                        arr1.add(All_Products.get(product).get(1));

                        // Update in the Total List
                        All_Products.put(product, (ArrayList<String>) arr1);

                        int cou = 0;
                        // int pri = 0;
                        try {
                            cou = Integer.parseInt(User_List.get(Current_User).U_Cart.get(product).get(0));
                            // pri = Integer.parseInt(Merchants.get(i).M_Products.get(product).get(1));
                        } catch (Exception e) {
                            cou = 0;
                        }

                        List<String> arr2 = new ArrayList<>();

                        arr2.add(Integer.toString(count + cou));
                        // arr2.add(Integer.toString(
                        // pri + Integer.parseInt(Merchants.get(i).M_Products.get(product).get(1)) *
                        // count));
                        arr2.add(Merchants.get(i).M_Products.get(product).get(1));
                        product = Merchants.get(i).M_Id + "-" + product;

                        // Update in the user's cart
                        User_List.get(Current_User).U_Cart.put(product, (ArrayList<String>) arr2);

                        System.out.println("--------Added to Cart SuccessFully--------");
                        not = 1;
                        break;

                    } else {
                        System.out.println("Your count is more then the available Product !");
                    }
                } else {
                    not = 1;
                }
            }
            if (not == 0) {
                System.out.println("User Not Found !");
            }
        }
    }

    // Use's Cart
    static void User_Cart() {
        while (true) {
            System.out.println(User_List.get(Current_User).U_Cart);
            System.out.println("1 => Buy \n2 => Back");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.println("Enter the Product Name :");
                String product = sc.next();
                sc.nextLine();

                try {

                    System.out.println("Enter the Product Count : ");
                    int count = sc.nextInt();

                    if (count <= Integer.parseInt(User_List.get(Current_User).U_Cart.get(product).get(0))) {
                        if (Integer.parseInt(User_List.get(Current_User).U_Cart.get(product).get(0)) > 0) {

                            if (count
                                    * Integer.parseInt(
                                            User_List.get(Current_User).U_Cart.get(product).get(1)) <= User_List
                                                    .get(Current_User).U_Money) {

                                List<String> arr = new ArrayList<>();

                                arr.add(Integer.toString(
                                        Integer.parseInt(User_List.get(Current_User).U_Cart.get(product).get(0))
                                                - count));
                                arr.add(User_List.get(Current_User).U_Cart.get(product).get(1));

                                // Update User Cart
                                User_List.get(Current_User).U_Cart.put(product, (ArrayList<String>) arr);

                                // Update Buy History
                                User_List.get(Current_User).U_Buy
                                        .add(java.time.LocalDateTime.now() + " ---Buy--- " + product + " ---Price--- "
                                                + count
                                                        * Integer.parseInt(User_List.get(Current_User).U_Cart
                                                                .get(product).get(1)));

                                // Update user Amount
                                User_List.get(Current_User).U_Money -= (count
                                        * Integer.parseInt(User_List.get(Current_User).U_Cart.get(product).get(1)));

                                System.out.println("------Success------");

                            } else {
                                System.out.println("Insuficient Amount In your Wallet !");
                            }
                        } else {
                            System.out.println("Product is Empty! :-)");
                        }
                    } else {
                        System.out.println("Count is more than the Available Count in the Cart !");
                    }
                } catch (Exception e) {
                    System.out.println("Product Not Found !");
                }
            } else if (ch == 2) {
                User_panel();
            } else {
                System.out.println("Please Enter the valid Chooise !");
            }
        }
    }

    // User's wallet
    static void Wallet() {
        while (true) {
            System.out.println("1 => Check Balance \n2 => Deposite \n3 => Statement \n4 => Back");
            int ch = sc.nextInt();

            // Check Balance
            if (ch == 1) {
                System.out.println("User Name : " + User_List.get(Current_User).U_Name + "\nAvailable Balance : "
                        + User_List.get(Current_User).U_Money);
            }

            // Deposite amount
            else if (ch == 2) {
                System.out.println("Enter the Amount : ");
                int amount = sc.nextInt();
                User_List.get(Current_User).Statement.add(
                        java.time.LocalDateTime.now() + "---Deposit---"
                                + (amount + User_List.get(Current_User).U_Money));
                User_List.get(Current_User).U_Money += amount;
                System.out.println("Amount Added Successfully !");
            }

            // Generate Mini Statement
            else if (ch == 3) {
                System.out.println("Mini Statement !");
                for (int k = User_List.get(Current_User).Statement.size() - 1; k >= 0; k--) {
                    System.out.println(User_List.get(Current_User).Statement.get(k));
                }
            }

            // Go back
            else if (ch == 4) {
                User_panel();
            }

            else {
                System.out.println("Enter the valid chooise !");
            }
        }
    }

    // User Registeration
    static void U_Register() {
        System.out.print("Register Page\nEnter the User User Name : ");
        String name = sc.next();
        sc.nextLine();
        System.out.print("Enter the Password : ");
        String password = sc.next();
        sc.nextLine();
        User_List.add(new Creat_User_Obj(name, password, U_Id));
        System.out.println("User Registered successfuly !\n\nYour U_Id : " + U_Id + "\n");
        U_Id += 1;
        U_Login();
    }

    public static void main(String args[]) {
        System.out.println("\tWelcome to Amazon.com \nChoose one of the following :");
        while (true) {
            System.out.println("1 => Admin\n2 => Merchant\n3 => User\n4 => Exit");
            int Chooise = sc.nextInt();
            if (Chooise == 1) {
                Clr_scr();
                Admin_Login();
            } else if (Chooise == 2) {
                Clr_scr();
                Merch();
            } else if (Chooise == 3) {
                Clr_scr();
                User();
            } else if (Chooise == 4) {
                Clr_scr();
                System.out.println("Thank you for your visit !");
                System.exit(0);
            } else {
                System.out.println("Please Enter the valid input !");
            }
        }
    }
}

class Create_Merchant_obj {
    public String M_Name, M_Password, M_Verified;
    public int M_Id;
    public Dictionary<String, ArrayList<String>> M_Products = new Hashtable<>();

    Create_Merchant_obj(String name, String password, String verified, int id) {
        this.M_Name = name;
        this.M_Password = password;
        this.M_Verified = verified;
        this.M_Id = id;
        // List<String> arr = new ArrayList<>();
        // arr.add("Count");
        // arr.add("Price");
        // this.M_Products.put("Product", (ArrayList<String>) arr);
    }
}

class Creat_User_Obj {
    public String U_Name, U_Password;
    public int U_Id, U_Money;
    public Dictionary<String, ArrayList<String>> U_Cart = new Hashtable<>();
    public List<String> Statement = new ArrayList<>();
    public List<String> U_Buy = new ArrayList<>();

    Creat_User_Obj(String name, String password, int id) {
        this.U_Name = name;
        this.U_Password = password;
        this.U_Id = id;
        this.U_Money = 0;
    }
}