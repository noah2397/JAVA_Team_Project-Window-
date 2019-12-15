#include <stdio.h>
#include <time.h>
#include <curses.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include <fcntl.h>
#include <pthread.h>
#include <math.h> //sin함수 쓰기위해 사용
#define PI 3.141592
#define X 30
#define Y 60
int i, k, l,quit=0;
float sine(int); //함수 선언
float cosine(int);
void print_edge();
void print_analogclock();
void* print_clock(void *z);
void DrawTime2(int h,int m,int s);
int delete_count = 0;
int cal_year, cal_month, cal_day;

void print(struct tm t, int unit);
int return_unit(int month, int year);
int is_Leaf(int year);
void schedule(struct tm t);
void search(int y, int m);
void print2(struct tm t, int unit);
void colon(int d);
void DrawNum(int dn,int n);
void DrawTime(int y,int m,int d);
void delete_schedule(struct tm t, int record);
void input_date(int y, int m, int d, char* input, int unit, struct tm *t);
void f(int signum){
	if(signum == 3) quit = 1;
	else if(signum == 2){
		char input[20];
		clear();
		move(0,0);
		mvaddstr(26,10,"enter 'q' or 'quit' to close this program   :  ");
		scanw("%s", input);
		if(strcmp(input,"q")==0 || strcmp(input,"quit")==0){
			endwin();
			exit(0);
		}
		else{
			clear();
			move(0,0);
			mvaddstr(26,10,"wrong input! press enter to continue");
			input[0] = getch();
			f(signum);
		}
	}
}
struct data{
	char line[10];
	char date[10];
	char data[50];
};

char*digits[10][5][4]=//0~9까지 출력할 정보
{
    {
        {"0","0","0","0"},
        {"0"," "," ","0"},
        {"0"," "," ","0"},
        {"0"," "," ","0"},
        {"0","0","0","0"}
    },
    {
        {" "," "," ","0"},
        {" "," "," ","0"},
        {" "," "," ","0"},
        {" "," "," ","0"},
        {" "," "," ","0"}
    },
    {
        {"0","0","0","0"},
        {" "," "," ","0"},
        {"0","0","0","0"},
        {"0"," "," "," "},
        {"0","0","0","0"}
    },
    {
        {"0","0","0","0"},
        {" "," "," ","0"},
        {"0","0","0","0"},
        {" "," "," ","0"},
        {"0","0","0","0"}
    },
    {
        {"0"," ","0"," "},
        {"0"," ","0"," "},
        {"0","0","0","0"},
        {" "," ","0"," "},
        {" "," ","0"," "}
    },
    {
        {"0","0","0","0"},
        {"0"," "," "," "},
        {"0","0","0","0"},
        {" "," "," ","0"},
        {"0","0","0","0"},
    }, 
    {
        {"0"," "," "," "},
        {"0"," "," "," "},
        {"0","0","0","0"},
        {"0"," "," ","0"},
        {"0","0","0","0"}
    },
    {
        {"0","0","0","0"},
        {"0"," "," ","0"},
        {"0"," "," ","0"},
        {" "," "," ","0"}, 
        {" "," "," ","0"}
    },
    {
        {"0","0","0","0"},
        {"0"," "," ","0"},
        {"0","0","0","0"},
        {"0"," "," ","0"},
        {"0","0","0","0"}
    },
    {
        {"0","0","0","0"},
        {"0"," "," ","0"},
        {"0","0","0","0"},
        {" "," "," ","0"},
        {" "," "," ","0"}
    }
};
char* col[5] = {" "," ","*"," "," "};


void main(void) {
	signal(SIGQUIT,f);
	signal(SIGINT, f);
	int today, yo, month,year;
	int unit;
	time_t timer;
	struct tm *t;
	char input[20];

	timer = time(NULL);
	t = localtime(&timer);
	
	
	month = t->tm_mon + 1;
	year = t->tm_year + 1900;
	unit = return_unit(month, year);
	print(*t, unit);

    	int i;
	initscr();

	while(1){
		clear();
		mvaddstr(0,0,">>");
		search(t->tm_year+1900,t->tm_mon + 1);
		DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
		print2(*t, unit);
		usleep(500);
		refresh();
		scanf("%s", input);
		if(strcmp(input,"0") == 0){ //저번달
			if(t->tm_mon+1 == 3 && t->tm_mday > 28){
				if(is_Leaf(year) && t->tm_mday == 29)
					;
				else
					timer -= 86400*(t->tm_mday - 28);
			}
			unit = return_unit(month-1, year);
			timer -= 86400 * unit;
			t = localtime(&timer);
			month = t->tm_mon + 1;
			year = t->tm_year + 1900;
			clear();
			unit = return_unit(month, year);
		}
		else if(strcmp(input,"1") == 0){ //다음달
			if(t->tm_mon+1 == 1 && t->tm_mday > 28){
				if(is_Leaf(year) && t->tm_mday == 29)
					;
				else
					timer -= 86400*(t->tm_mday - 28);
			}
			timer += 86400 * unit;
			t = localtime(&timer);
			month = t->tm_mon + 1;
			year = t->tm_year + 1900;
			clear();
			unit = return_unit(month, year);
		}
		else if(strcmp(input,"2") == 0){//일정 입력
			clear();
			move(0,0);
			printw(">>%s",input);
			DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
			print2(*t, unit);
			mvaddstr(28,10,"< Input schedule >\n");
			refresh();
			schedule(*t);
			clear();
		}
		else if(strcmp(input,"3") == 0){//일정 입력
			clear();
			move(0,0);
			printw(">>%s",input);
			int num;
			DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
			print2(*t, unit);
			mvaddstr(28,10,"< Input number of month for minus >\n");
			mvaddstr(29,15,"month for minus");
			move(29,10);
			scanw("%d",&num);
			for(int i=0; i<num; i++){
			if(t->tm_mon+1 == 3 && t->tm_mday > 28){
				if(is_Leaf(year) && t->tm_mday == 29)
					;
				else
					timer -= 86400*(t->tm_mday - 28);
			}
			unit = return_unit(month-1, year);
			timer -= 86400 * unit;
			t = localtime(&timer);
			month = t->tm_mon + 1;
			year = t->tm_year + 1900;
			unit = return_unit(month, year);
			}
			
		}
		else if(strcmp(input,"4") == 0){//일정 입력
			clear();
			move(0,0);
			printw(">>%s",input);
			int num;
			DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
			print2(*t, unit);
			mvaddstr(28,10,"< Input number of month for plus >\n");
			mvaddstr(29,15,"month for plus");
			move(29,10);
			scanw("%d",&num);
			for(int i=0; i<num; i++){
			if(t->tm_mon+1 == 1 && t->tm_mday > 28){
				if(is_Leaf(year) && t->tm_mday == 29)
					;
				else
					timer -= 86400*(t->tm_mday - 28);
			}
			timer += 86400 * unit;
			t = localtime(&timer);
			month = t->tm_mon + 1;
			year = t->tm_year + 1900;
			clear();
			unit = return_unit(month, year);
			}
			
		}
		else if(strcmp(input,"5") == 0){//일정 삭제
			clear();
			move(0,0);
			printw(">>%s",input);
			int record;
			delete_count ++;
			DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
			print2(*t, unit);
			mvaddstr(28,10,"< What record you want to delete? >\n");
			mvaddstr(29,10,"delete        record");
			move(29,18);
			scanw("%d",&record);
			delete_schedule(*t,record);
			clear();
		}
		else if(strcmp(input,"6") == 0){//일정 계산기
			time_t rawtime;
			struct tm *timeinfo;
			const char* weekday[] = {"Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday"};
			int cal_yday, cur_yday, cur_year;
			cur_yday = t->tm_yday;
			cur_year = t->tm_year + 1900;
			input_date(cal_year, cal_month, cal_day, input, unit, t);
			time(&rawtime);
			timeinfo = localtime(&rawtime);
			timeinfo->tm_year = cal_year - 1900;
			timeinfo->tm_mon = cal_month - 1;
			timeinfo->tm_mday = cal_day;
			mktime(timeinfo);
			clear();
			move(0,0);
			printw(">>%s",input);
			DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
			print2(*t, unit);
			move(28,10);
			printw("the date you typed : %d.%d.%d is on ", cal_year,
			cal_month, cal_day);
			standout();
			printw("%s\n", weekday[timeinfo->tm_wday]);
			standend();
			cal_yday = timeinfo->tm_yday;
			move(29,10);
			if(cal_year < cur_year)
				cal_yday -= 365 * (cur_year-cal_year);
			else if(cal_year > cur_year)
				cal_yday += 365 * (cal_year-cur_year);
			if(cal_yday > cur_yday)
				printw("%d days left from that day", cal_yday-cur_yday);
			else
				printw("%d days elapsed from today", cur_yday-cal_yday);
			timer = time(NULL);
			t = localtime(&timer);
			mvaddstr(32,10,"Press ""enter"" to continue!");
			input[0] = getch();
			refresh();
		}
		else if(strcmp(input,"7") == 0)
			print_analogclock();
		else if(strcmp(input,"114") == 0){//사용 설명서
			clear();
			move(0,0);
			printw(">>%s",input);
			DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
			print2(*t, unit);
			mvaddstr(28,10," 0 + enter : last month\n");
			mvaddstr(29,10," 1 + enter : next month\n");
			mvaddstr(30,10," 2 + enter : input schedule\n");
			mvaddstr(31,10," 3 + enter : last month (far)\n");
			mvaddstr(32,10," 4 + enter : next month (far)\n");
			mvaddstr(33,10," 5 + enter : delete schedule\n");
			mvaddstr(34,10," 6 + enter : mini schedule calculator\n");
			mvaddstr(35,10," 7 + enter : Analog + Digital Wall Clock\n");
			mvaddstr(38,10," Press ""enter"" to continue!\n");
			mvaddstr(39,10," Press Ctrl + c to close\n");
			input[0] = getch();
			refresh();
		}
		else{//잘못된 입력 처리
			clear();
			move(0,0);
			printw(">>%s",input);
			DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
			print2(*t, unit);
			mvaddstr(28,10,"That input is not supported, please try again!\n");
			mvaddstr(29,10,"Press ""enter"" to continue!\n");
			input[0] = getch();
			refresh();
		}
	}	

	endwin();


	
}

void delete_schedule(struct tm t, int record){
	
	char comp[10];
	char month[10];
	sprintf(comp,"%d", t.tm_year + 1900);
	sprintf(month,"%d",t.tm_mon + 1);
	strcat(comp,"-");
	strcat(comp,month);
	struct data output,insert;
	strcpy(insert.line,"=====");
	strcpy(insert.date,"9999-99");
	strcpy(insert.data," ");
	FILE *f;
	int num_rec=0;
	int delete_rec = 0;
	char buf[100];
	f = fopen("read.txt","r+");
	while(feof(f)==0){
		fread(&output, sizeof(output), 1, f);
		delete_rec++;
		if(feof(f)!=0)
			break;

		if(strcmp(output.date,comp)==0){
			num_rec++;
			if(num_rec == record){
	 			fseek(f,sizeof(output)*(delete_rec-1),SEEK_SET);
				fwrite(&insert, sizeof(insert), 1, f);
				break;	
			}	
		}
	}
	fclose(f);
}
void print(struct tm t, int unit){
	printf("\t %d월 %d년 %d일\n",  t.tm_mon + 1, t.tm_year + 1900, t.tm_mday);
	printf(" 일 월 화 수 목 금 토\n");
	int today = t.tm_mday;
	int yo = t.tm_wday;
	while(today != 1){
		today -= 1;
		yo -= 1;
		if(yo == -1)
			yo+=7;
	}
	for(int i=0; i<yo; i++){
		printf("   ");
	}
	for(int i=0; i<unit; i++)
	{
		printf("%3d", today);
		today++; yo++;
		if(yo == 7){
			printf("\n");
			yo = 0;
		}
	}	
	printf("\n");
}


int return_unit(int month, int year){
	int unit;
	if(month < 1){
		month = 12;
		year -= 1;
	}
	if(month > 12){
		month == 1;
		year += 1;
	}
	if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
		unit = 31;
	else if(month == 2){
		if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
			unit = 29;
		else
			unit = 28;
	}
	else
		unit = 30;
	return unit;
}

int is_Leaf(int year){
	int check;
	if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) check = 1;
	else check = 0;
	return check;
}
void schedule(struct tm t){

	move(29,10);
	FILE *input;
	char c[100];

	char year[10];
	char month[10];
	sprintf(year,"%d", t.tm_year + 1900);
	sprintf(month,"%d", t.tm_mon + 1);
	strcat(year,"-");
	strcat(year,month);
	struct data insert;
	input = fopen("read.txt","a+");
	strcpy(insert.date,year);
	strcpy(insert.line,"=====");
	while(1){
		scanw("%[^\n]s",c);
		strcpy(insert.data,c);
		fwrite(&insert, sizeof(insert), 1, input);
		fclose(input);
		break;
	}

}
void search(int y, int m){
	int index =30; 
	char content[100];
	char year[10];
	char month[10];
	sprintf(year,"%d", y);
	sprintf(month,"%d", m);
	strcat(year,"-");
	strcat(year,month);
	struct data output;
	FILE *input;
	input = fopen("read.txt","a+");
	fclose(input);
	input = fopen("read.txt","r");	
	
	while(feof(input)==0){
		fread(&output, sizeof(output), 1, input);
		if(feof(input)!=0)
			break;
		if(strcmp(year,output.date) == 0){
			move(index++,10);
			printw("%s\n", output.data);
		}

	}
	fclose(input);
	refresh();

}
void print2(struct tm t, int unit){
	start_color();
	init_color(0,0,0,0);
	init_pair(1, 1, 0);
	init_pair(2, 4, 0);
	init_pair(3, 7, 0);
	int x = 12, y = 10;
	move(9, 10);
	attron(COLOR_PAIR(1));
	addstr("SUN             ");
	attroff(COLOR_PAIR(1));
	addstr("MON             ");
	addstr("TUE             ");
	addstr("WED             ");
	addstr("THU             ");
	addstr("FRI             ");
	attron(COLOR_PAIR(2));
	addstr("SAT             \n");
	attron(COLOR_PAIR(3));
	int today = t.tm_mday;
	int yo = t.tm_wday;
	while(today != 1){
		today -= 1;
		yo -= 1;
		if(yo == -1)
			yo+=7;
	}
	move(x,y);
	for(int i=0; i<yo; i++){
		y+=16;
		move(x, y);
		printf("             ");
	}
	for(int i=0; i<unit; i++)
	{
		if(yo == 6)
			attron(COLOR_PAIR(2));
		if(yo == 0)
			attron(COLOR_PAIR(1));
		if(today == t.tm_mday){
		standout();
		printw("%d", today);
		standend();
		}
		else
			printw("%d", today);
		attron(COLOR_PAIR(3));	
		y+=16;
		move(x,y);
		today++; yo++;
		if(yo == 7){
			addstr("\n");
			x += 3;
			y-=112;
			move(x, y);
			
			yo = 0;
		}
	}
	mvaddstr(38,10, " 114 + enter : show me a manual\n");	
}
void colon(int d)
{
    move(7, d*10);
    for(int y=0;y<5;y++)
    {
        addstr(col[y]);
    }
}

void DrawNum(int dn,int n)
{
    int y,x;
    
    move(2, dn*10);

    for(y=0;y<5;y++)
    {

        for(x=0;x<4;x++)
        {
            addstr(digits[n][y][x]);
        }
	addstr("\n");
	move(2+y+1, dn*10);
    }
}
//날짜 출력하는함수
void DrawTime(int y,int m,int d)
{
    DrawNum(1,y/1000);
    y= y%1000;
    DrawNum(2,y/100);
    y= y%100;
    DrawNum(3,y/10);
    DrawNum(4,y%10);
    colon(5);
    DrawNum(6,m/10);//분의 앞자리
    DrawNum(7,m%10);//분의 뒷자리
    colon(8);
    DrawNum(9,d/10);//초의 앞자리
    DrawNum(10,d%10);//초의 뒷자리
}
//시간 출력하는 함수
void DrawTime2(int h,int m,int s)
{
    DrawNum(2,h/10);
    DrawNum(3,h%10);
    colon(4);
    DrawNum(5,m/10);//분의 앞자리
    DrawNum(6,m%10);//분의 뒷자리
    colon(7);
    DrawNum(8,s/10);//초의 앞자리
    DrawNum(9,s%10);//초의 뒷자리
}
void input_date(int y, int m, int d, char* input, int unit, struct tm *t)
{
	clear();
	move(0,0);
	printw(">>%s",input);
	DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
	print2(*t, unit);
	mvaddstr(28,10,"< Input a date that you want to know >\n");
	mvaddstr(29,10,"Input year: ");
	scanw("%d", &y);
	mvaddstr(30,10,"Input month: ");
	scanw("%d", &m);
	mvaddstr(31,10,"Input day: ");
	scanw("%d", &d);
	cal_year = y;
	cal_month = m;
	cal_day = d;
	if(((d > 31 || d < 1) || (m > 12 || m < 1))){
		clear();
		move(0,0);
		printw(">>%s",input);
		DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
		print2(*t, unit);
		mvaddstr(28,10,"Can't calculate, please try again");
		mvaddstr(32,10,"Press ""enter"" to continue!");
		input[0] = getch();
		input_date(y, m, d, input, unit, t);
		refresh();
	}
}
void print_edge(){ //여기서부터는 아날로그 시계를 출력하는 함수 소스들
	float sin,cos;
	for(int i=0; i<=360; i++){

        	sin = sine(i)*17+X;
		cos = cosine(i)*32+Y;
		mvaddstr((int)sin, (int)cos, "0");
		refresh();
	}
	mvaddstr(12, 60, """12""");
	mvaddstr(47, 60, """6""");
	mvaddstr(30, 95, """3""");
	mvaddstr(30, 25, """9""");
}
 
float sine(int sec)
{
        float temp;         
        temp = sin((sec*(PI/180))-(PI/180)*90);
	return temp;
}

float cosine(int sec)
{
        float temp;         
        temp = cos((sec*(PI/180))-(PI/180)*90);
	return temp;
}
void print_analogclock(){ //직접적인 아날로그 시계 함수의 main문

	void *print_clock(void*);
	pthread_t t1,t2,t3;
	initscr();
	clear();
	char input;
	float ssin,scos,msin,mcos,hsin,hcos;
	time_t timer;
	struct tm *t;
	while(1){
		timer = time(NULL);
		t = localtime(&timer);
		i = t->tm_sec * 6;
		k = t->tm_min * 6;
		l = t->tm_hour * 15;
		print_edge();
		DrawTime2(t->tm_hour, t->tm_min, t->tm_sec);
		pthread_create(&t1, NULL, print_clock, (void*)"sec");
		pthread_create(&t2, NULL, print_clock, (void*)"min");
		pthread_create(&t3, NULL, print_clock, (void*)"hour");
	
		pthread_join(t1, NULL);
		pthread_join(t2, NULL);
		pthread_join(t3, NULL);

        	ssin = sine(i)*15+X;
		scos = cosine(i)*30+Y;
		msin = sine(k)*15+X;
		mcos = cosine(k)*30+Y;
		hsin = sine(l)*15+X;
		hcos = cosine(l)*30+Y;

		mvaddstr((int)ssin, (int)scos, "-");
		refresh();
		sleep(1);
		clear();
		if(quit == 1){
			quit = 0;
			break;
		}

	}
}
void* print_clock(void *z){ //쓰레드를 사용하여 병렬적으로 처리
	char* way = (char*) z;
	float a,b,c,d,e,f;
	if(strcmp(way,"sec")==0){
		for(int j=0; j<15; j++){
        		a = sine(i)*j+X;
			b = cosine(i)*(j*2)+Y;
			mvaddstr((int)a, (int)b, "o");
		}
	}
	if(strcmp(way,"min")==0){
		for(int j=0; j<10; j++){
        		c = sine(k)*j+X;
			d = cosine(k)*(j*2)+Y;
			mvaddstr((int)c, (int)d, "O");
		}
	}
	if(strcmp(way,"hour")==0){
		for(int j=0; j<5; j++){
        		e = sine(l)*j+X;
			f = cosine(l)*(j*2)+Y;
			mvaddstr((int)e, (int)f, "x");
		}	
	}

}
