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
#define X 30
#define Y 60

float sine(int); //함수 선언
float cosine(int);
void print_edge();

int main(void){
	initscr();
	clear();
	float ssin,scos,msin,mcos,hsin,hcos,a,b,c,d,e,f;
	time_t timer;
	struct tm *t;
	int i, k, l;

	timer = time(NULL);
	t = localtime(&timer);
	i = t->tm_sec * 6;
	k = t->tm_min * 6;
	l = t->tm_hour * 15;

	while(1){
		print_edge();
        	ssin = sine(i)*15+X;
		scos = cosine(i)*30+Y;
		msin = sine(k)*15+X;
		mcos = cosine(k)*30+Y;
		hsin = sine(l)*15+X;
		hcos = cosine(l)*30+Y;
		for(int j=0; j<15; j++){
        		a = sine(i)*j+X;
			b = cosine(i)*(j*2)+Y;
			mvaddstr((int)a, (int)b, "o");
		}
		for(int j=0; j<10; j++){
        		c = sine(k)*j+X;
			d = cosine(k)*(j*2)+Y;
			mvaddstr((int)c, (int)d, "O");
		}
		for(int j=0; j<5; j++){
        		e = sine(l)*j+X;
			f = cosine(l)*(j*2)+Y;
			mvaddstr((int)e, (int)f, "x");
		}
		mvaddstr((int)ssin, (int)scos, "-");
		refresh();
		sleep(1);
		clear();
		i += 6;
		if(i % 360 == 0)
			k += 6;
		if(k % 360 == 0)
			l += 15;
	}
	endwin();
}

void print_edge(){
	float sin,cos;
	for(int i=0; i<=360; i++){

        	sin = sine(i)*17+X;
		cos = cosine(i)*32+Y;
		mvaddstr((int)sin, (int)cos, "0");
		refresh();
	}
	mvaddstr(12, 60, "12");
}
 
float sine(int sec)
{
        float temp;         
        temp = sin((sec*(PI/180)));
	return temp;
}

float cosine(int sec)
{
        float temp;         
        temp = cos((sec*(PI/180)));
	return temp;
}
