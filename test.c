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
pthread_mutex_t counter_lock = PTHREAD_MUTEX_INITIALIZER;
float sine(int); //함수 선언
float cosine(int);
void print_edge();
/*void* print_clock(void *f){
	char* way = (char*) f;
	float sin,cos,a,b;
	if(strcmp(way,"sec")==0){
		for(int i=0; i<=360; i++){
        		sin = sine(i)*15+30;
			cos = cosine(i)*30+60;
			pthread_mutex_lock(&counter_lock);
			mvaddstr((int)sin, (int)cos, "*");
			pthread_mutex_unlock(&counter_lock);
			usleep(50000);
			refresh();
		}
	}
	if(strcmp(way,"min")==0){
		for(int i=0; i<=360; i++){
        		a = sine(i)*10+30;
			b = cosine(i)*20+60;
			pthread_mutex_lock(&counter_lock);
			mvaddstr((int)a, (int)b, "0");
			pthread_mutex_unlock(&counter_lock);
			usleep(50000);
			refresh();
		}
	}



}*/

int main(void){
	initscr();
	clear();
	pthread_t t1,t2;
	float sin,cos,a,b;
	void *print_clock(void*);
	/*pthread_create(&t1, NULL, print_clock, (void*)"sec");
	pthread_create(&t2, NULL, print_clock, (void*)"min");
	
	pthread_join(t1, NULL);
	pthread_join(t2, NULL);*/


		for(int i=0; i<=360; i+=6){

			print_edge();
        		sin = sine(i)*15+X;
			cos = cosine(i)*30+Y;
			for(int j=1; j<15; j++){
        			a = sine(i)*j+X;
				b = cosine(i)*(j*2)+Y;
				mvaddstr((int)a, (int)b, "-");
				usleep(500);
			}
			mvaddstr((int)sin, (int)cos, "1");
			refresh();
			sleep(1);
			clear();
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


