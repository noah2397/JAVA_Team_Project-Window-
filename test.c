#include <stdio.h>
#include <time.h>
#include <curses.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include <fcntl.h>
#include <stdio.h>
#include <pthread.h>

#include <math.h> //sin함수 쓰기위해 사용
#define PI 3.141592
float sine(int); //함수 선언
float cosine(int);

void* print_clock(void *f){
	char* way = (char*) f;
	float sin,cos,a,b;
	if(strcmp(way,"sec")!=0){
		for(int i=0; i<=360; i++){

        		sin = sine(i)*15+30;
			cos = cosine(i)*30+60;
			mvaddstr(30,60, "*");
			mvaddstr((int)sin, (int)cos, "*");

			usleep(50000);
			refresh();
		}
	}
	if(strcmp(way,"min")!=0){
		for(int i=0; i<=360; i++){

        		sin = sine(i)*15+30;
			cos = cosine(i)*30+60;
			mvaddstr(30,60, "*");
			mvaddstr((int)a, (int)b, "0");

			usleep(50000);
			refresh();
		}
	}



}

int main(void){
	initscr();
	clear();
	float sin,cos;
	float a,b;
	float c,d;
	pthread_t t1,t2;
	void *print_clock(void*);
	pthread_create(&t1, NULL, print_clock, (void*)"sec");
	pthread_create(&t2, NULL, print_clock, (void*)"min");
	for(int i=0; i<=360; i++){

        	sin = sine(i)*17+30;
		cos = cosine(i)*32+60;
		mvaddstr((int)sin, (int)cos, "0");
		refresh();
	}
	pthread_join(t1, NULL);
	pthread_join(t2, NULL);
	endwin();

		


}

 
float sine(int sec)
{
        float temp;         
        temp = sin((sec*(PI/180)));
        //printf("sin(%.2d) = %+.2lf\n",sec*6,temp*30);
	return temp;
        
}
float cosine(int sec)
{
        float temp;         
        temp = cos((sec*(PI/180)));
        //printf("cos(%.2d) = %+.2lf\n",sec*6,temp*30);
	return temp;
        
}


