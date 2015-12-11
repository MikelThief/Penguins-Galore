#include <stdio.h>

int main(){
    int menu;
    printf("What do you want to do? ");
    printf("\n1. Start the game ");
    printf("\n2. Show instructions ");
    printf("\n3. Exit game \n");
    scanf("%d", &menu);

        switch(menu){
            case 1: {
                //call probably DrawBoard.c or other files
                break;
            }
            case 2: {
                //show instructions
                break;
            }
            case 3: {
                printf("\nGoodbye!");
                break;
            }
            default: {
                printf("\nGoodbye!");
                break;
            }

        }

    return 0;
}
