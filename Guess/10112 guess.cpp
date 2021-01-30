#include<bits/stdc++.h>
int main()
{
    long long int n,i,mem[120];
    mem[1]=1;
    mem[2]=1;
    mem[3]=1;
    for(i=4; i<120; i++)
    {
        mem[i]=mem[i-1]+mem[i-3];
    }
    while((scanf("%lld",&n)==1)&&n!=0)
    {
        printf("%lld\n",mem[n-2016]);
    }
    return 0;
}
