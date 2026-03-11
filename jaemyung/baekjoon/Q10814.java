import java.util.*;

public class Q10814 {
    public static class User {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        User[] users = new User[n];
        
        for (int i = 0; i < n; i++) {
            int age = sc.nextInt();
            String name = sc.next();
            
            users[i] = new User(age, name);
        }
        
        Arrays.sort(users, (u1, u2) -> u1.age - u2.age);
        
        for (int i = 0; i < users.length; i++) {
			System.out.println(users[i].age + " " + users[i].name);
		}
    }
}