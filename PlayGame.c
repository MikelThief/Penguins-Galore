#include "main.h"

void PlayGame(Pointer PointerX1, Pointer PointerY1, Pointer PointerDir1, Pointer PointerSpaces1, Pointer PointerPengID1, Pointer PointerX2, Pointer PointerY2, Pointer PointerDir2, Pointer PointerSpaces2, Pointer PointerPengID2) {

    // initializations//
    HANDLE  hConsole;
    hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    int NumberOfRows, NumberOfColumns, AllPengs = 0, i, j, PengID = 0, Spaces = 0, Dir, X, Y, moved, idRow, score1 = 0, score2 = 0; //AllPengs should be the full amount of penguins in the future
    int failsafe = 0, endsafe = 0;
    char filename[128], name[128];
    const char* ext = ".map";
    Again:
    //Taking data from user//
    printf("Set board:");
    printf("\n1. Generate your own board");
    printf("\n2. Read board from file\n");
    scanf("%i", &j);

    switch(j) {
        case 1: {
            printf("\nEnter number of rows: \n");
            scanf("%i", &NumberOfRows);
            NumberOfRows += 2;

            printf("Enter number of columns: \n");
            scanf("%i", &NumberOfColumns);
            NumberOfColumns += 2;
            break;
        }
        case 2: {
            while(1) {
                printf("Enter a filename to be loaded: \n");
                scanf(" %s", name);
                if(sizeof(filename) > strlen(name) + 1) {
                    strncpy(filename, name, sizeof(filename));
                    if(sizeof(filename) > (strlen(filename) + strlen(ext) + 1)) {
                        strncat(filename, ext, (sizeof(filename) - strlen(filename)));
                        if(FileExists(filename)) {
                            break;
                        } else
                            printf("The file doesn't exist!\n");
                    } else
                        printf("Filename is too long!\n");
                } else
                    printf("Filename is too long!\n");
            }

            NumberOfRows = LoadRoC(filename, 1);
            NumberOfColumns = LoadRoC(filename, 2);
            AllPengs = LoadRoC(filename, 3);
            i = LoadRoC(filename, 4);
            score1 = LoadRoC(filename, 5);
            score2 = LoadRoC(filename, 6);
            break;
        }
        default: {
                printf("Give a proper choice!\n");
                goto Again;
                break;
            }
    }

    if(AllPengs == 0) {
        printf("Enter number of penguins for each player: \n");
        scanf("%i", &AllPengs);
        AllPengs = AllPengs * 2;
    }

    int FishArray[NumberOfRows][NumberOfColumns]; //surrounded my 0 fishes floes
    int PengArray[AllPengs][3]; /* Columns: AllPengs, x coord, y coord of penguin? So one row per penguin.
                                AllPengs to check if the player is allowed to move the penguin */
    switch(j) {
        case 1: {
            Cleaner(NumberOfRows, NumberOfColumns, FishArray);
            MapGenerator(NumberOfRows, NumberOfColumns, FishArray);
            break;
        }
        case 2: {
            LoadBoard(filename, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
            break;
        }
    }

    if(score1 == 0 && score2 == 0) {
        Cleaner(AllPengs, 3, PengArray);
        DrawBoard(NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);

        //Manual loop//
        i = 0;
        //Peng placing
        while(i < AllPengs) {
            if(i % 2 == 0) {
                SetConsoleTextAttribute(hConsole, 14);
                printf("Player1\n");
                X = PointerX1(Spaces, PengID, i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
                Y = PointerY1(Spaces, PengID, i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
            } else if(i % 2) {
                SetConsoleTextAttribute(hConsole, 12);
                printf("Player2\n");
                X = PointerX2(Spaces, PengID, i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
                Y = PointerY2(Spaces, PengID, i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
            }

            if(PosPeng(X, Y, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
                DrawBoard(NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
                i++;
            } else {
                SetConsoleTextAttribute(hConsole, 7);
                printf("You violated the rules! Try to place penguin again!\n");
                Sleep(700);
                DrawBoard(NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
            }
        }
        i = 1;
    }

    DrawBoard(NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
    PrintCoords(score1, score2, AllPengs, PengArray);
    printf("Turn %d\n", i);
    while(CheckEnd(0, NumberOfColumns, FishArray, AllPengs, PengArray) == 0) {
        moved = 0;
        if(i % 2 && CheckEnd(1, NumberOfColumns, FishArray, AllPengs, PengArray) == 0) {
            moved = 1;
            if(AllPengs == 2) {
                PengID = 1;
            } else {
                SetConsoleTextAttribute(hConsole, 14);
                printf("Player1\n");
                PengID = PointerPengID1(Spaces, PengID, i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
            }
        } else if(i % 2 == 0 && CheckEnd(2, NumberOfColumns, FishArray, AllPengs, PengArray) == 0) {
            moved = 1;
            if(AllPengs == 2) {
                PengID = 2;
            } else {
                SetConsoleTextAttribute(hConsole, 12);
                printf("Player2\n");
                PengID = PointerPengID2(Spaces, PengID, i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
            }
        } else {
            i++;
        }

        if (PengID % 2 == i % 2 && moved == 1 && WhichPenguin(PengID, AllPengs, PengArray) != -1) {
            moved = 0;
            if(i % 2 && CheckEnd(1, NumberOfColumns, FishArray, AllPengs, PengArray) == 0) {
                SetConsoleTextAttribute(hConsole, 14);
                Spaces = PointerSpaces1(Spaces, PengID, i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
                Dir = PointerDir1(Spaces, PengID, i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
            } else if(i % 2 == 0 && CheckEnd(2, NumberOfColumns, FishArray, AllPengs, PengArray) == 0) {
                SetConsoleTextAttribute(hConsole, 12);
                Spaces = PointerSpaces2(Spaces, PengID, i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
                Dir = PointerDir2(Spaces, PengID, i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
            }

            SetConsoleTextAttribute(hConsole, 7);

            switch(Dir) {
                case 1: {
                    //go NE
                    if(MovePengNE(PengID, Spaces, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray)) {
                        printf("\nYou moved to NE");
                        moved = 1;
                    } else {
                        printf("\nIllegal move!");
                        if(failsafe == 10) {
                            i++;
                            failsafe = 0;
                            endsafe++;
                        }
                        failsafe++;
                    }
                    break;
                }
                case 2: {
                    //go E
                    if(MovePengE(PengID, Spaces, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray)) {
                        printf("\nYou moved to E");
                        moved = 1;
                    } else {
                        printf("\nIllegal move!");
                        if(failsafe == 10) {
                            i++;
                            failsafe = 0;
                            endsafe++;
                        }
                        failsafe++;
                    }
                    break;
                }
                case 3: {
                    //go SE
                    if(MovePengSE(PengID, Spaces, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray)) {
                        printf("\nYou moved to SE");
                        moved = 1;
                    } else {
                        printf("\nIllegal move!");
                        if(failsafe == 10) {
                            i++;
                            failsafe = 0;
                            endsafe++;
                        }
                        failsafe++;
                    }
                    break;
                }
                case 4: {
                    // go SW
                    if(MovePengSW(PengID, Spaces, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray)) {
                        printf("\nYou moved to SW");
                        moved = 1;
                    } else {
                        printf("\nIllegal move!");
                        if(failsafe == 10) {
                            i++;
                            failsafe = 0;
                            endsafe++;
                        }
                        failsafe++;
                    }
                    break;
                }
                case 5: {
                    // go W
                    if(MovePengW(PengID, Spaces, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray)) {
                        printf("\nYou moved to W");
                        moved = 1;
                    } else {
                        printf("\nIllegal move!");
                        if(failsafe == 10) {
                            i++;
                            failsafe = 0;
                            endsafe++;
                        }
                        failsafe++;
                    }
                    break;
                }
                case 6: {
                    //go NW
                    if(MovePengNW(PengID, Spaces, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray)) {
                        printf("\nYou moved to NW");
                        moved = 1;
                    } else {
                        printf("\nIllegal move!");
                        if(failsafe == 10) {
                            i++;
                            failsafe = 0;
                            endsafe++;
                        }
                        failsafe++;
                    }
                    break;
                }
                default: {
                    printf("\nGive me the number one more time! ");
                    break;
                }
            }
        } else if(moved == 1) {
            moved = 0;
            printf("\nYou chose wrong penguin! Try again!");
        }
        DrawBoard(NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);

        if(moved == 1) {
            endsafe = 0;
            failsafe = 0;
            idRow = WhichPenguin(PengID, AllPengs, PengArray);
            if(i % 2) {
                score1 = Score(score1, PengArray[idRow][1], PengArray[idRow][2], NumberOfColumns, FishArray);
            } else if(i % 2 == 0) {
                score2 = Score(score2, PengArray[idRow][1], PengArray[idRow][2], NumberOfColumns, FishArray);
            }
            i++;
            SaveBoard("game.map", i, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray, score1, score2);
        }
        if(endsafe == 10)
            break; //Jump to game end if no one has moved for 10 turns

        PrintCoords(score1, score2, AllPengs, PengArray);
        printf("Turn %d\n", i);
        Sleep(100);
    }

    if (score1 > score2)
        printf("Player1 won! with %i points!", score1);
    else if(score1 == score2)
        printf("It's a tie! Both players have %i points!", score1);
    else
        printf("Player2 won! with %i points!", score2);
    printf("\nGame OVER!");
    getch();
    system ( "cls" );

}

