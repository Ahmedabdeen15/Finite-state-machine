import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class

public class App {
    public static void main(String[] args) {
        int T = 0;
        ArrayList<state> fsmtable = new ArrayList<state>();
        ArrayList<Integer> end = new ArrayList<Integer>();
        boolean flag;
        Scanner scan = new Scanner(System.in); // Create a Scanner object
        System.out.println("how many states in your machine?");
        int n = scan.nextInt();
        int start, current_state, no_inputs, flaginvalid = 0;
        String test, TEMP, inputs[];
        System.out.println("how many inputs in your machine?");
        no_inputs = scan.nextInt();
        inputs = new String[no_inputs];
        for (int i = 0; i < no_inputs; i++) {
            System.out.println("Enter input " + i + " :");
            inputs[i] = scan.next();
        }
        for (int i = 0; i < n; i++) {
            TEMP = "In state S" + i;
            fsmtable.add(new state(inputs, i, TEMP));

        }
        System.out.println("what is the start states for your machine?");
        start = scan.nextInt();
        System.out.println("how meny final states your machine have?");
        n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("what is the final state " + (i + 1) + " for your machine?");
            end.add(scan.nextInt());
        }
        char x;
        do {
            System.out.println("Enter string needed to be tested:");
            test = scan.next();
            current_state = start;
            flaginvalid = 0;
            flag = false;
            for (char c : test.toCharArray()) {

                for (int j = 0; j < fsmtable.get(current_state).inputs.size(); j++) {
                    if (fsmtable.get(current_state).inputs.get(j).input.charAt(0) == c) {
                        T++;
                        System.out.println(T + ") Form S" + current_state + "to S"
                                + fsmtable.get(current_state).inputs.get(j).nex_state);
                        current_state = fsmtable.get(current_state).inputs.get(j).nex_state;
                        flaginvalid++;
                    }
                }
                if (flaginvalid == 0) {
                    break;
                }
            }

            if (flaginvalid == 0) {
                System.err.println("invalid input");
            } else {
                for (int i = 0; i < end.size(); i++) {
                    if (current_state == end.get(i))
                        flag = true;
                }
                if (flag)
                    System.out.println("String valid");
                else
                    System.out.println("String is not valid");
            }
            System.out.println("Do you wont to test another string?\n (y/n):");
            x = scan.next().charAt(0);
        } while (x != 'n' && x != 'N');

    }

}

class state {

    ArrayList<inputs> inputs = new ArrayList<inputs>();
    int stateno;

    state(String[] inputs, int stateno, String TEMP) {
        Scanner scan = new Scanner(System.in); // Create a Scanner object

        this.stateno = stateno;
        int next_state;
        String input;
        for (int i = 0; i < inputs.length; i++) {
            input = inputs[i];

            System.out.print(TEMP + " enter next state for input " + input + ":\nS");
            next_state = scan.nextInt();

            this.inputs.add(new inputs(next_state, input));
        }
    }

}

class inputs {
    int nex_state;
    String input;

    inputs(int nexstate, String input) {
        this.input = input;
        this.nex_state = nexstate;
    }
}