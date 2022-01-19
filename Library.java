import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.lang.*;

class Book {
    String name;
    int isbn;
    int count;
    int cost;
}

class Admin {
    String name;
    String pin;
}

class Borrower {
    ArrayList<Book> cart = new ArrayList<>();
    ArrayList<LocalTime> borrowtime = new ArrayList<>();
    String name;
    String pin;
    int money = 1500;
}

class Library {
    public static Book addbook(ArrayList<Book> y) {
        Scanner sc = new Scanner(System.in);
        Book x = new Book();
        System.out.println("Enter book name : ");
        x.name = sc.nextLine();
        System.out.println("Enter book isbn number : ");
        int isbn = sc.nextInt();
        Boolean is = true;
        for (int i = 0; i < y.size(); i++) {
            if (isbn == y.get(i).isbn) {
                is = false;
            }
        }
        if (is) {
            x.isbn = isbn;
        } else {
            x.isbn = -1;
        }
        System.out.println("Enter book count : ");
        x.count = sc.nextInt();
        return x;
    }

    public static int rembooks(ArrayList<Book> y) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < y.size(); i++) {
            System.out.println(i + "-" + y.get(i).name);
        }
        System.out.println("Select the book you wnt to remove : ");
        int x = sc.nextInt();
        if ((x < y.size()) && (x >= 0)) {
            return x;
        } else {
            return -1;
        }
    }

    public static int add2cart(ArrayList<Book> x) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the book you want to borrow : ");
        int res = -1;
        for (int i = 0; i < x.size(); i++) {
            System.out.println(i + "-" + x.get(i).name);
        }
        int v = sc.nextInt();
        if ((v >= 0) && (v < x.size())) {
            res = v;
        }
        return res;
    }

    public static int removefromcart(ArrayList<Book> x) {
        System.out.println("Select the Bok You want to return : ");
        Scanner sc = new Scanner(System.in);
        int res = -1;
        for (int i = 0; i < x.size(); i++) {
            System.out.println(i + "-" + x.get(i).name);
        }
        int v = sc.nextInt();
        if ((v >= 0) && (v < x.size())) {
            res = v;
        }
        return res;
    }

    public static int valid_admin(ArrayList<Admin> x) {
        int val = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your name : ");
        String nme = sc.next();
        System.out.println("Enter Your pin : ");
        String pword = sc.next();
        for (int i = 0; i < x.size(); i++) {
            if ((x.get(i).name.equals(nme)) && (x.get(i).pin.equals(pword))) {
                val = i;
            }
        }
        return val;
    }

    public static int valid_user(ArrayList<Borrower> x) {
        int val = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your name : ");
        String nme = sc.next();
        System.out.println("Enter Your pin : ");
        String pword = sc.next();
        for (int i = 0; i < x.size(); i++) {
            if ((x.get(i).name.equals(nme)) && (x.get(i).pin.equals(pword))) {
                val = i;
            }
        }
        return val;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Borrower> users = new ArrayList<>();
        Borrower b = new Borrower();
        Book x = new Book();
        x.count = 3;
        x.isbn = 1;
        x.name = "Life Of Pi";
        x.cost = 250;
        Admin a = new Admin();
        b.name = "Bala";
        b.pin = "1111";
        a.name = "Admin";
        a.pin = "1111";
        books.add(x);
        users.add(b);
        admins.add(a);
        Boolean whole_exit = false;
        while (!whole_exit) {
            System.out.println("1-admin");
            System.out.println("2-user");
            System.out.println("3-exit");
            System.out.println("Enter Your Choice : ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    int al = valid_admin(admins);
                    if (al != -1) {
                        Boolean admin_exit = false;
                        while (!admin_exit) {
                            System.out.println("1-Add book");
                            System.out.println("2-remove book");
                            System.out.println("3-Edit book");
                            System.out.println("4-View books");
                            System.out.println("5-Show Borrowed Book List");
                            System.out.println("6-Exit");
                            System.out.println("Enter Your Choice : ");
                            int ach = sc.nextInt();
                            switch (ach) {
                                case 1:
                                    Book ab = addbook(books);
                                    if (ab.isbn != -1) {
                                        books.add(ab);

                                        System.out.println("Book added successfully");
                                    } else {
                                        System.out.println("Duplicate isbn , Book not Added");
                                    }
                                    break;
                                case 2:
                                    books.remove(rembooks(books));
                                    break;
                                case 3:
                                    for (int i = 0; i < books.size(); i++) {
                                        System.out.println(i + "-" + books.get(i).name);
                                    }
                                    System.out.println("Enter the book you want to edit : ");
                                    int eb = sc.nextInt();
                                    if ((eb < books.size()) && (eb >= 0)) {
                                        System.out.println("0-name");
                                        System.out.println("2-count");
                                        System.out.println("Enter Your Choice");
                                        int c = sc.nextInt();
                                        if ((c >= 0) && (c < 2)) {
                                            if (c == 0) {
                                                books.get(eb).name = sc.next();
                                            } else {
                                                books.get(eb).count = sc.nextInt();
                                            }
                                        } else {
                                            System.out.println("Enter a valid choice");
                                        }
                                    } else {
                                        System.out.println("Enter a valid book.");
                                    }
                                    break;
                                case 4:
                                    for (int i = 0; i < books.size(); i++) {
                                        System.out.println("name : " + books.get(i).name);
                                        System.out.println("isbn : " + books.get(i).isbn);
                                        System.out.println("count : " + books.get(i).count);
                                    }
                                    break;
                                case 5:
                                    for (int i = 0; i < users.size(); i++) {
                                        System.out.println(users.get(i).name);
                                        for (int j = 0; j < users.get(i).cart.size(); j++) {
                                            System.out.println(users.get(i).cart.get(j).name);
                                        }
                                        System.out.println();
                                    }
                                    break;
                                case 6:
                                    admin_exit = true;
                                    break;
                                default:
                                    System.out.println("Enter a valid option");
                            }
                        }
                    } else {
                        System.out.println("Credential Error");
                    }
                    break;
                case 2:
                    int ul = valid_user(users);
                    if (ul != -1) {
                        Boolean user_exit = false;
                        while (!user_exit) {
                            System.out.println("1-Borrow Book");
                            System.out.println("2-Return Book");
                            System.out.println("3-Check Wallet Balance");
                            System.out.println("4-Add Amount to Wallet");
                            System.out.println("5-View My Cart");
                            System.out.println("6-Exit");
                            int uc = sc.nextInt();
                            switch (uc) {
                                case 1:
                                    if (users.get(ul).money > 500) {
                                        int bb = add2cart(books);
                                        if (bb != -1) {
                                            Boolean alb = true;
                                            for (int i = 0; i < users.get(ul).cart.size(); i++) {
                                                if (users.get(ul).cart.get(i).name == books.get(i).name) {
                                                    alb = false;
                                                }
                                            }
                                            if (alb) {
                                                users.get(ul).cart.add(books.get(bb));
                                                users.get(ul).borrowtime.add(LocalTime.now());
                                                books.get(bb).count -= 1;
                                            } else {
                                                System.out.println("Yoy! already have this book");
                                            }
                                        }
                                    } else {
                                        System.out.println("Insufficient Wallet amount");
                                    }
                                    break;
                                case 2:
                                    int rb = removefromcart(users.get(ul).cart);
                                    if (rb != -1) {
                                        int f = -1;
                                        for (int i = 0; i < books.size(); i++) {
                                            if (books.get(i).name == users.get(ul).cart.get(rb).name) {
                                                f = i;
                                            }
                                        }
                                        LocalTime t1 = users.get(ul).borrowtime.get(rb);
                                        LocalTime t2 = LocalTime.now();
                                        long finetime = ChronoUnit.HOURS.between(t1, t2) * 60
                                                + ChronoUnit.MINUTES.between(t1, t2);
                                        if (finetime < 1) {
                                            books.get(f).count += 1;
                                            users.get(ul).cart.remove(rb);
                                            users.get(ul).borrowtime.remove(rb);
                                            System.out.println("Book returned in-time");
                                        } else {
                                            System.out.println("Book returning date expired");
                                            long fineamount = 0;
                                            finetime -= 1;
                                            int theit = 1;
                                            while (finetime > 0) {
                                                if (finetime >= 10) {
                                                    fineamount += 10 * ((int) Math.pow(2, theit));
                                                    finetime -= 10;
                                                } else {
                                                    fineamount += finetime * ((int) Math.pow(2, theit));
                                                    finetime = 0;
                                                }
                                                theit += 1;
                                            }
                                            if (fineamount > users.get(ul).cart.get(rb).cost * 0.8) {
                                                fineamount = (long) users.get(ul).cart.get(rb).cost / 2;
                                            }
                                            System.out.println("Your fine amount is : " + fineamount);
                                            System.out.println("1-pay from wallet");
                                            System.out.println("2-pay by other means");
                                            System.out.println("3-Not now");
                                            System.out.println("Enter Your Choice : ");
                                            int fc = sc.nextInt();
                                            switch (fc) {
                                                case 1:
                                                    users.get(ul).money -= fineamount;
                                                    users.get(ul).cart.remove(rb);
                                                    users.get(ul).borrowtime.remove(rb);
                                                    break;
                                                case 2:
                                                    users.get(ul).cart.remove(rb);
                                                    users.get(ul).borrowtime.remove(rb);
                                                    break;
                                            }
                                        }
                                    }
                                    break;
                                case 3:
                                    System.out.println("Your balance is " + users.get(ul).money);
                                    break;
                                case 4:
                                    System.out.println("Enter the amount to be added to wallet : ");
                                    int amt = sc.nextInt();
                                    if (amt > 0) {
                                        users.get(ul).money += amt;
                                        System.out.println("Amount Added to your wallet");
                                    } else {
                                        System.out.println("Enter a valid amount");
                                    }
                                    break;
                                case 5:
                                    for (int i = 0; i < users.get(ul).cart.size(); i++) {
                                        System.out.println(users.get(ul).cart.get(i).name);
                                    }
                                    break;
                                case 6:
                                    user_exit = true;
                            }
                        }
                    } else {
                        System.out.println("Entered Credentials Doesnot match");
                    }
                    break;
                case 3:
                    whole_exit = true;
                    break;
                default:
                    System.out.println("Enter a valid number");
            }
        }
    }
}