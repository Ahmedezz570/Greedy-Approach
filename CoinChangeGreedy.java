package Greedy;

import java.util.Arrays;

public class CoinChangeGreedy {
    public static void main(String[] args) {
    int[] coins = {12 , 10 , 5 , 1 , 1 , 1 , 1};
    int amount = 16;

    int result = coinChange(coins, amount);
    if (result != -1) {
        System.out.println("Minimum coins needed: " + result);
    } else {
        System.out.println("Not possible to make the amount with given coins.");
    }
}
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        int i = coins.length - 1;

        while (amount > 0 && i >= 0) {
            if (coins[i] <= amount) {
                count += amount / coins[i];
                amount %= coins[i];
            }
            i--;
        }

        return amount == 0 ? count : -1;
    }


}