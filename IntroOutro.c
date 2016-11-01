#include "main.h"

void Intro(){
    PlaySound("resources/penguins.wav", NULL, SND_ASYNC); // Plays sound file; need to add winmm in Settings -> Compiler ->                                      //                                              ->Linker Settings
    char *filename = "resources/penguinsgalore.txt";
    FILE *fptr = NULL;
    if((fptr = fopen(filename,"r")) == NULL)
    {
        fprintf(stderr,"error opening %s\n",filename);
    }
    AsciiArtPrinter(fptr);
    fclose(fptr);
    Sleep(1000);
    char *filename2 = "resources/peng.txt";
    FILE *fptr2 = NULL;
    if((fptr2 = fopen(filename2,"r")) == NULL)
    {
        fprintf(stderr,"error opening %s\n",filename);
    }
    AsciiArtPrinter(fptr2);
    fclose(fptr2);
    Sleep(1000);
}

void Outro(){
//Something when game will end?
}
