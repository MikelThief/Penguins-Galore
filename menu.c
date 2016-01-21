#include "main.h"

int main() {

    Intro();

    Pointer PointerX1, PointerY1, PointerDir1, PointerSpaces1, PointerPengID1, PointerX2, PointerY2, PointerDir2, PointerSpaces2, PointerPengID2;
    int menu, p, i = 0;
    for(;;) {
        printf("What do you want to do? ");
        printf("\n1. Start the game ");
        printf("\n2. Show instructions ");
        printf("\n3. Exit game \n");

        scanf("%d", &menu);
        //choosing Player1 algorithm
        switch(menu) {
            case 1: {
                printf("Set game parameters now!");

                while(i == 0) {
                    Sleep(700);
                    system ( "cls" );
                    printf("Choose Player1's algorithm:\n");
                    printf("1. Manual Override\n");
                    printf("2. Dummy\n");
                    printf("3. Center\n");
                    printf("4. Julian Tian's algorithm\n");
                    printf("5. Photon - Michal Bator\n");
                    printf("6. Teemu\n");

                    scanf("%i", &p);
                    switch(p) {
                        case 1: {
                            printf("You chose Manual Override as Player1's algorithm!\n");
                            PointerX1 = &UserEnterCrdX;
                            PointerY1 = &UserEnterCrdY;
                            PointerDir1 = &UserEnterDir;
                            PointerSpaces1 = &UserEnterNumOfSpaces;
                            PointerPengID1 = &UserEnterPengID;
                            i = 1;
                            break;
                        }
                        case 2: {
                            printf("You chose Dummy as Player1's algorithm!\n");
                            PointerX1 = &DummyCrdX;
                            PointerY1 = &DummyCrdY;
                            PointerDir1 = &DummyDir;
                            PointerSpaces1 = &DummySpaces;
                            PointerPengID1 = &DummyPengID;
                            i = 1;
                            break;
                        }
                        case 3: {
                            printf("You chose Center as Player1's algorithm!\n");
                            PointerX1 = &CenterCrdX;
                            PointerY1 = &CenterCrdY;
                            PointerDir1 = &CenterDir;
                            PointerSpaces1 = &CenterSpaces;
                            PointerPengID1 = &CenterPengID;
                            i = 1;
                            break;
                        }
                        case 4: {
                            printf("You chose Julian Tian's algorithm as Player1's algorithm!\n");
                            PointerX1 = &JulianCrdX;
                            PointerY1 = &JulianCrdY;
                            PointerDir1 = &JulianDir;
                            PointerSpaces1 = &JulianSpaces;
                            PointerPengID1 = &JulianPengID;
                            i = 1;
                            break;
                        }
                        case 5: {
                            printf("Photon - Michal Bator");
                            PointerX1 = &PhotonCrdX;
                            PointerY1 = &PhotonCrdY;
                            PointerDir1 = &PhotonDir;
                            PointerSpaces1 = &PhotonSpaces;
                            PointerPengID1 = &PhotonPengID;

                            i = 1;
                            break;
                        }
                        case 6: {
                            printf("Teemu");
                            PointerX1 = &TeemuCrdX;
                            PointerY1 = &TeemuCrdY;
                            PointerDir1 = &TeemuDir;
                            PointerSpaces1 = &TeemuSpaces;
                            PointerPengID1 = &TeemuPengID;
                            i = 1;
                            break;
                        }
                        default: {
                            printf("Set a proper parameter!\n");
                            break;
                        }
                    }
                }

                i = 0;

                while(i == 0) {
                    Sleep(700);
                    system ( "cls" );
                    printf("Choose Player2's algorithm:\n");
                    printf("1. Manual Override\n");
                    printf("2. Dummy\n");
                    printf("3. Center\n");
                    printf("4. Julian Tian's algorithm\n");
                    printf("5. Photon - Michal Bator\n");
                    printf("6. Teemu\n");

                    scanf("%i", &p);
                    switch(p) {
                        case 1: {
                            printf("You chose Manual Override as Player2's algorithm!\n");
                            PointerX2 = &UserEnterCrdX;
                            PointerY2 = &UserEnterCrdY;
                            PointerDir2 = &UserEnterDir;
                            PointerSpaces2 = &UserEnterNumOfSpaces;
                            PointerPengID2 = &UserEnterPengID;
                            i = 1;
                            break;
                        }
                        case 2: {
                            printf("You chose Dummy as Player2's algorithm!\n");
                            PointerX2 = &DummyCrdX;
                            PointerY2 = &DummyCrdY;
                            PointerDir2 = &DummyDir;
                            PointerSpaces2 = &DummySpaces;
                            PointerPengID2 = &DummyPengID;
                            i = 1;
                            break;
                        }
                        case 3: {
                            printf("You chose Center as Player2's algorithm!\n");
                            PointerX2 = &CenterCrdX;
                            PointerY2 = &CenterCrdY;
                            PointerDir2 = &CenterDir;
                            PointerSpaces2 = &CenterSpaces;
                            PointerPengID2 = &CenterPengID;
                            i = 1;
                            break;
                        }
                        case 4: {
                            printf("You chose Julian Tian's algorithm as Player1's algorithm!\n");
                            PointerX2 = &JulianCrdX;
                            PointerY2 = &JulianCrdY;
                            PointerDir2 = &JulianDir;
                            PointerSpaces2 = &JulianSpaces;
                            PointerPengID2 = &JulianPengID;
                            i = 1;
                            break;
                        }
                        case 5: {
                            printf("Photon - Michal Bator");
                            PointerX2 = &PhotonCrdX;
                            PointerY2 = &PhotonCrdY;
                            PointerDir2 = &PhotonDir;
                            PointerSpaces2 = &PhotonSpaces;
                            PointerPengID2 = &PhotonPengID;
                            i = 1;
                            break;
                        }
                        case 6: {
                            printf("Teemu");
                            PointerX2 = &TeemuCrdX;
                            PointerY2 = &TeemuCrdY;
                            PointerDir2 = &TeemuDir;
                            PointerSpaces2 = &TeemuSpaces;
                            PointerPengID2 = &TeemuPengID;
                            i = 1;
                            break;
                        }
                        default: {
                            printf("Set a proper parameter!\n");
                            break;
                        }
                    }
                }
                Sleep(700);
                system ( "cls" );

                //run game
                PlayGame(PointerX1, PointerY1, PointerDir1, PointerSpaces1, PointerPengID1, PointerX2, PointerY2, PointerDir2, PointerSpaces2, PointerPengID2);
                break;
            }
            case 2: {
                printf("Welcome to the Penguin Galore!\n");
printf("The aim of the game is to collect fish from ice floats using your penguin.\n");
printf("Ice floats contains from 1 to 3 fish.\n");
printf("He forced us to use some stupid algorithms called 'AI'...\n");
printf("They will know what to do.\n");
printf("They can move in 6 directions.\n");
printf("In a straight line (horizontal, vertical, diagonal)\n");
printf("You can move by the number of ice floats of your choice\n");
printf("Game end when last penguin has no possibility to move:");
printf("\n\t He stands at the last ice floats.");
printf("\n\t Between him and other possible ice floats there is always 1-float gap.");
printf("\nThe winner is the player with biggest number of fish.");
            }
            case 3: {
                printf("\nGoodbye!");
                exit(0);
            }
            default : {
                printf("Give a proper choice!\n");
                break;
            }
        }
    }
}


