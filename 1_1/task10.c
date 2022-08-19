/*
 * Coin change problem: 
 * A common problem to find minimum number of coins that add up given amount.
 * Better ways with dynamic programming can be implemented. 
 * But for the simpility's sake, we can simply use recursion.
 */
//TODO:
//~
//
#include <stdio.h>

int coin_change(int coins[], int n, int m);

int main()
{
	int amount;
	printf("Enter amount of money: ");
	scanf("%d", &amount);
	int coins[] = {1,2,5,10,20,50,100,200};
	int n = sizeof(coins)/sizeof(coins[0]);
	printf("\n%d \n", coin_change(coins, n, amount));
	return 0;
}
int coin_change(int coins[], int n, int m)
{
	if (m == 0) return 1;
	if (m < 0) return 0;
	if (n <= 0) return 0;
	return coin_change(coins, n - 1, m) + coin_change(coins, n, m - coins[n-1]);
}
