#include<bits/stdc++.h>
using namespace std;
int main()
{
    freopen("words.txt","r",stdin);
    freopen("wordsEnglish.txt","w",stdout);
    int in =0;
    char line[100];
    srand (time(NULL));
    while(gets(line))
    {
        in++;
        if(in>10000)
        {
            line[0]=tolower(line[0]);
            printf("%s %d\n",line,rand() % 10 + 1);

        }

        if(in>10100) break;
    }

}
