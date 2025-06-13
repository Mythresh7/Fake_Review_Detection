#include<bits/stdc++.h>
using namespace std;
string f(string &s,string &t)
{
   int ans=1e9;
   int strt=-1;
   if(s.length()<t.length())
   return "";
   
   unordered_map<char> m1,m2;
   for(char ch: m1)
   {
    m2[ch]++;
   }
   int l=0,r=0;
   while(r<s.length())
   {
    char ch=s[r];
    m1[ch]++;
    
    while(l<r && m1==m2)
    {
        ans=min(ans,r-l+1);
        strt=l;
        m1[s[l]]--;
        l++;
    }
    r++;
   }

}
int main()
{
    string s,t;
    cin>>s>>t;
    return f(s,t);
}