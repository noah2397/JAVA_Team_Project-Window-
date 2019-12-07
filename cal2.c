#include <stdio.h>
#include <time.h>
#include <curses.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
int delete_count = 0;

void print(struct tm t, int unit);
int return_unit(int month, int year);
int is_Leaf(int year);
void schedule(struct tm t);
void search(int y, int m);
void print2(struct tm t, int unit);
void colon(int d);
void DrawNum(int dn,int n);
void DrawTime(int y,int m,int d);
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

void delete_schedule(struct tm t, int record){
	
	char comp[10];
	char month[10];
	sprintf(comp,"%d", 2019);
	sprintf(month,"%d", 12);
	strcat(comp,"-");
	strcat(comp,month);

	FILE *f;
	int num_rec=0, length=0;
	int delete_rec = 0;
	char buf[100];
	f = fopen("read.txt","r+");
	while(fscanf(f,"%s\n",buf) != EOF){
	delete_rec++;
	length+=strlen(buf);
		if(strcmp(buf,comp)==0){
			num_rec++;
			if(num_rec == record){
				fscanf(f,"%[^\n]s\n",buf);
	 			fseek(f,length,SEEK_SET);
				fprintf(f,"X");
				break;	
			}	
		}
	}
	fclose(f);
}
void main(void) {
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
	unit = return_unit(month, year);
	print(*t, unit);

    	int i;
	initscr();

	while(1){
		clear();
		move(0,0);
		printw(">>%d",input);
		search(t->tm_year+1900,t->tm_mon + 1);
		DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
		print2(*t, unit);
		usleep(500);
		refresh();
		scanf("%d", &input);
		if(input == 0){ //저번달
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
		else if(input == 1){ //다음달
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
		else if(input == 2){//일정 입력
			clear();
			DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
			print2(*t, unit);
			mvaddstr(28,10,"< Input schedule >\n");
			refresh();
			schedule(*t);
			clear();
		}
		else if(input == 3){//일정 입력
			clear();
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
		else if(input == 4){//일정 입력
			clear();
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
		else if(input == 5){//일정 삭
			clear();
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
		else if(input == 119){//사용 설명서
			clear();
			move(0,0);
			printw(">>%d",input);
			DrawTime(t->tm_year+1900,t->tm_mon + 1,t->tm_mday);
			print2(*t, unit);
			mvaddstr(28,10," 0 + enter : last month\n");
			mvaddstr(29,10," 1 + enter : next month\n");
			mvaddstr(30,10," 2 + enter : input schedule\n");
			mvaddstr(31,10," 3 + enter : last month (far)\n");
			mvaddstr(32,10," 4 + enter : next month (far)\n");
			mvaddstr(33,10," 5 + enter : delete schedule\n");
			mvaddstr(38,10," Press ""enter"" to continue!\n");
			input = getch();
			refresh();
		}

		

	}
	endwin();


	
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
	input = fopen("read.txt","a+");
	fprintf(input, "=====\n" );
	fprintf(input, "%d-", t.tm_year + 1900 );
	fprintf(input, "%d\n", t.tm_mon + 1);


	while(1){
		scanw("%[^\n]s",c);
		fprintf(input, "%s\n", c);
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
	
	FILE *input;
	input = fopen("read.txt","a+");
	fclose(input);
	input = fopen("read.txt","r");	
	
	while(fscanf(input,"%s\n",content) != EOF){
		if(strcmp(year,content) == 0){
			fscanf(input,"%[^\n]s\n",content);
			move(index++,10);
			printw("%s\n", content);
		}
		if(content == NULL)
			break;

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
//시간을 출력하는함수
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
