import java.util.Scanner;

class Time {
    int hour;
    int minute;
}

class TrainInfo {
    int train_no;
    String train_name;
    String start_st;
    String end_st;
    Time dept_time;
    Time arr_time;
}

class Main {
    static final int MAX = 100;

    public static void train_edit(TrainInfo[] train, int[] no_of_trains) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int train_num;
        TrainInfo temp;
        while (true) {
            System.out.println("\t\t*TRAIN EDIT MENU*\n");
            System.out.println("1. Add Train.\n");
            System.out.println("2. Delete Train.\n");
            System.out.println("3. Exit Train Edit Menu.\n");
            System.out.print("Your Choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (no_of_trains[0] >= MAX) {
                        System.out.println("\nError! There are already maximum trains.\n\n");
                        break;
                    }
                    System.out.print("\nInput Train Number: ");
                    temp = new TrainInfo();
                    temp.train_no = scanner.nextInt();
                    for (int i = 0; i < no_of_trains[0]; i++) {
                        if (train[i].train_no == temp.train_no) {
                            System.out.println("Error! Train number " + temp.train_no + " already exists. Please try again.\n\n");
                            temp.train_no = -1;
                            break;
                        }
                    }
                    if (temp.train_no == -1)
                        break;
                    System.out.print("Input Train Name: ");
                    scanner.nextLine();
                    temp.train_name = scanner.nextLine();
                    System.out.print("Input Start Station: ");
                    temp.start_st = scanner.nextLine();
                    System.out.print("Input End Station: ");
                    temp.end_st = scanner.nextLine();
                    System.out.print("Input Departure Time: \n");
                    System.out.print("Hour: ");
                    temp.dept_time = new Time();
                    temp.dept_time.hour = scanner.nextInt();
                    System.out.print("Minute: ");
                    temp.dept_time.minute = scanner.nextInt();
                    System.out.print("Input Arrival Time: \n");
                    System.out.print("Hour: ");
                    temp.arr_time = new Time();
                    temp.arr_time.hour = scanner.nextInt();
                    System.out.print("Minute: ");
                    temp.arr_time.minute = scanner.nextInt();
                    train[no_of_trains[0]] = temp;
                    for (int i = no_of_trains[0]; i >= 1; i--) {
                        if (train[i - 1].train_no >= train[i].train_no) {
                            temp = train[i - 1];
                            train[i - 1] = train[i];
                            train[i] = temp;
                        } else {
                            break;
                        }
                    }
                    no_of_trains[0]++;
                    System.out.println("Train " + train[no_of_trains[0] - 1].train_no + " added successfully.\n\n");
                    break;
                case 2:
                    if (no_of_trains[0] == 0) {
                        System.out.println("\nError! No Train Available.\n\n");
                        break;
                    }
                    System.out.print("\nInput Train Number: ");
                    train_num = scanner.nextInt();
                    for (int i = 0; i < no_of_trains[0]; i++) {
                        if (train[i].train_no == train_num) {
                            while (i < no_of_trains[0] - 1) {
                                train[i] = train[i + 1];
                                i++;
                            }
                            no_of_trains[0]--;
                            System.out.println("Train " + train_num + " deleted successfully.\n\n");
                            train_num = -1;
                            break;
                        }
                    }
                    if (train_num != -1)
                        System.out.println("Error! Train " + train_num + " not found\n\n");
                    break;
                case 3:
                    System.out.println();
                    return;
                default:
                    System.out.println("\nError! Wrong Choice.\n\n");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] no_of_trains = {0}; // Use an array to pass by reference
        int choice;
        String dept_st, arr_st;
        Time train_time;
        TrainInfo[] train = new TrainInfo[MAX];
        train_edit(train,  no_of_trains);
        while (true) {
            System.out.println("\t\t\t*MENU*\n");
            System.out.println("1. List all the trains departed from a particular station.\n");
            System.out.println("2. List all the trains departed from a particular station at a particular time.\n");
            System.out.println("3. List all the trains departed from particular station within the next one hour of a given time.\n");
            System.out.println("4. List all the trains between a pair of start station and end station.\n");
            System.out.println("5. Edit train details.\n");
            System.out.println("6. Exit.\n");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n\t\t*INPUT DETAILS*\n");
                    System.out.print("Depart Station: ");
                    scanner.nextLine();
                    dept_st = scanner.nextLine();
                    for (int i = 0; i < no_of_trains[0]; i++) {
                        if (train[i].start_st.equals(dept_st)) {
                            System.out.println(train[i].train_no + "\t" + train[i].train_name + "\t" + train[i].start_st + "\t" + train[i].end_st + "\t"
                                    + train[i].dept_time.hour + ":" + train[i].dept_time.minute + "\t"
                                    + train[i].arr_time.hour + ":" + train[i].arr_time.minute);
                        }
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println("\n\t\t*INPUT DETAILS*\n");
                    System.out.print("Depart Station: ");
                    scanner.nextLine();
                    dept_st = scanner.nextLine();
                    System.out.println("Train Time: \n");
                    System.out.print("Hour: ");
                    train_time = new Time();
                    train_time.hour = scanner.nextInt();
                    System.out.print("Minute: ");
                    train_time.minute = scanner.nextInt();
                    for (int i = 0; i < no_of_trains[0]; i++) {
                        if (train[i].start_st.equals(dept_st) && train[i].dept_time.hour == train_time.hour
                                && train[i].dept_time.minute == train_time.minute) {
                            System.out.println(train[i].train_no + "\t" + train[i].train_name + "\t" + train[i].start_st + "\t" + train[i].end_st + "\t"
                                    + train[i].dept_time.hour + ":" + train[i].dept_time.minute + "\t"
                                    + train[i].arr_time.hour + ":" + train[i].arr_time.minute);
                        }
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println("\n\t\t*INPUT DETAILS*\n");
                    System.out.print("Depart Station: ");
                    scanner.nextLine();
                    dept_st = scanner.nextLine();
                    System.out.println("Time: \n");
                    System.out.print("Hour: ");
                    train_time = new Time();
                    train_time.hour = scanner.nextInt();
                    System.out.print("Minute: ");
                    train_time.minute = scanner.nextInt();
                    for (int i = 0; i < no_of_trains[0]; i++) {
                        if (train[i].start_st.equals(dept_st) && ((train[i].dept_time.hour == train_time.hour
                                && train[i].dept_time.minute >= train_time.minute)
                                || (train[i].dept_time.hour == train_time.hour + 1
                                && train[i].dept_time.minute <= train_time.minute))) {
                            System.out.println(train[i].train_no + "\t" + train[i].train_name + "\t" + train[i].start_st + "\t" + train[i].end_st + "\t"
                                    + train[i].dept_time.hour + ":" + train[i].dept_time.minute + "\t"
                                    + train[i].arr_time.hour + ":" + train[i].arr_time.minute);
                        }
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println("\n\t\t*INPUT DETAILS*\n");
                    System.out.print("Depart Station: ");
                    scanner.nextLine();
                    dept_st = scanner.nextLine();
                    System.out.print("Arrival Station: ");
                    arr_st = scanner.nextLine();
                    for (int i = 0; i < no_of_trains[0]; i++) {
                        if (train[i].start_st.equals(dept_st) && train[i].end_st.equals(arr_st)) {
                            System.out.println(train[i].train_no + "\t" + train[i].train_name + "\t" + train[i].start_st + "\t" + train[i].end_st + "\t"
                                    + train[i].dept_time.hour + ":" + train[i].dept_time.minute + "\t"
                                    + train[i].arr_time.hour + ":" + train[i].arr_time.minute);
                        }
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    train_edit(train, no_of_trains);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("\nError! Wrong Choice.\n\n");
            }
        }
    }
}

