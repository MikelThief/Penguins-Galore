#include <stdio.h>

int main(){
    int dir;
    char ctrl; // if you wish to continue...
    do{
        printf("\nPlease give me the direction: ");
        scanf("%d", &dir);

            switch(dir){
                case 1:{
                    //go NW
                    printf("\nYou moved to NW");
                    break;
                }
                case 2:{
                    //go NE
                    printf("\nYou moved to NE");
                    break;
                }
                case 3:{
                    //go W
                    printf("\nYou moved to W");
                    break;
                }
                case 4:{
                    // go E
                    printf("\nYou moved to E");
                    break;
                }
                case 5:{
                    // go SW
                    printf("\nYou moved to SW");
                    break;
                }
                case 6:{
                    //go SE
                    printf("\nYou moved to SE");
                    break;
                }
                default:{
                    printf("\nGive me the number one more time! ");
                    break;
                }

            }

            printf("\nDo you wish to continue? y/n ");
            scanf("%s", &ctrl);
    }while(ctrl != 'n');
    printf("\nBye!");


    return 0;
}
