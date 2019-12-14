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
void* print_clock(void *f){
	int way = (int) f;
	
	




}
float sine(int); //함수 선언
float cosine(int);
int main(void){
	initscr();
	clear();
	float sin,cos;
	float a,b;
	float c,d;

	pthread_t t1,t2;
	void *print_clock(void*);
	pthread_create(&t1, NULL, print_clock, (void*)0);
	pthread_create(&t2, NULL, print_clock, (void*)1);
	for(int i=0; i<=360; i++){

        	sin = sine(i)*17+30;
		cos = cosine(i)*32+60;
		mvaddstr((int)sin, (int)cos, "0");
		refresh();
	}
	for(int i=0; i<=360; i++){

        	sin = sine(i)*15+30;
		cos = cosine(i)*30+60;
        	a = sine(i)*10+30;
		b = cosine(i)*20+60;
		mvaddstr(30,60, "*");
		mvaddstr((int)sin, (int)cos, "*");
		mvaddstr((int)a, (int)b, "0");

		usleep(50000);
		refresh();
	}
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
/*void main(void) {
	signal(SIGQUIT,SIG_IGN);
	int today, yo, month,year;
	int unit;
	time_t timer;
	struct tm *t;
	int input;

	timer = time(NULL);
	t = localtime(&timer);
	
	
	month = t->tm_mon + 1;
	year = t->tm_year + 1900;


    	int i;
	


	
}*/


