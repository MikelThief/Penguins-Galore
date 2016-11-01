#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int MikebotXcord(int a, int PID, int b, int NumOfCols,int FishArray[NumOfRows][NumOfCols],int Allpengs, int PengArray[AllPengs][3])
{
    int j,k,x;
    do{
        x= rand()%(NumOfRows-1)+1;

    }while(CheckPeng(j,k,Allpengs,PengArray)==1);
    return x;
}
int MikebotYcord(int a, int PID, int b, int NumOfCols, int NumOfCols,int FishArray[NumOfRows][NumOfCols],int Allpengs, int PengArray[AllPengs][3])
{
    int j,k,y;
    do{
        y= rand()%(NumOfCols-1)+1;

    }while(CheckPeng(j,k,Allpengs,PengArray)==1);
    return y;
}
int MikeRandDirrection(int a, int PID, int b, int NumOfRows, int NumOfCols, int Fisharray[NumofRows][NumOfCols],int AllPengs,int Pengarray[AllPengs][3])
    {srand(time(NULL));
    int r=rand()%6;
    if( CheckMove(idRow, 1, 1, Row, Col, NumOfCols, FishArray, AllPengs, PengArray)==1)
    {
   returning:switch(r)
       {
       case 0:
           {
               if( CheckMove(idRow, 1, 1, Row, Col, NumOfCols, FishArray[NumofRows][NumOfCols], AllPengs, PengArray[AllPengs][3])==1)
               {

               return r;
               break;
               }

           }
           else
           {
            r=1;
            goto returning;
           }

       case 1:
           if( CheckMove(idRow, 2, 1, Row, Col, NumOfCols, FishArray, AllPengs, PengArray[AllPengs][3])==1)
               {
               return r;
               break;
               }
           else
           {
            r=2;
            goto returning;
           }

       case 2:
           if( CheckMove(idRow, 3, 1, Row, Col, NumOfCols, FishArray, AllPengs, PengArray[AllPengs][3])==1)
               {
               return r;
               break;
               }
           else
           {
            r=3;
            goto returning;
           }

       case 3:
           if( CheckMove(idRow, 4, 1, Row, Col, NumOfCols, FishArray, AllPengs, PengArray[AllPengs][3])==1)
               {
               return r;
               break;
               }
           else
           {
            r=4;
            goto returning;
           }

       case 4:
           if( CheckMove(idRow, 5, 1, Row, Col, NumOfCols, FishArray, AllPengs, PengArray[AllPengs][3])==1)
               {
               return r;
               break;
               }
           else
           {
            r=5;
            goto returning;
           }

       case 5:
           if( CheckMove(idRow, 6, 1, Row, Col, NumOfCols, FishArray, AllPengs, PengArray[AllPengs][3])==1)
               {

               return r;
               break;
               }
           else
           {
            r=1;
            goto returning;
           }

       }
    }
    }
        {
            return 0;
        }

int MikeRandpeng(int a, int PID,int b, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs,int PengArray[Allpengs][3] )
{
 int    j,k;
 if (b%2)
 {
     if(AllPengs==2)
     {
         return 1;
     }
 }
    for(j=0;j<allpengs;j++)
    {
        if(j%2==0)
        {
            for(k=1;k<=6;k++)
            {
                if(checkmove(j,k,1,NumOfCols,FishArray,AllPengs,PengArray))
                {
                    return ++j;
                }
            }
        }
    }
  else if(a%2==0){
    if(allpengs==2){
        return 2;
    }
    for(j=0;j<AllPengs;i++){
        if(j%2){
            for(k=1;k<=6;k++){
                if(checkmove(j,k,1,NumOfCols,FishArray,AllPengs,PengArray)){
                    return ++j;
                }
            }
        }
    }
  }
  return 0;
}
int NumberofSpaces(int a, int PID,int b, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs,int PengArray[Allpengs][3] )
{
    return 1;
}
