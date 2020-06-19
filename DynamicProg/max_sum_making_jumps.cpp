//can make a jump of 1,2,3 

#include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;
int main(){
    ll n ;
    cin >> n ;
    vector <ll> a(n) ;
    for(ll i=0;i<n;i++){
        cin >> a[i] ;
    }
    vector <ll> dp(n+1,0) ;
    dp[0] = a[0] ;
    dp[1] = dp[0] + a[1] ;
    dp[2]=  a[2] + max(dp[0],dp[1]) ;
    for(ll i=3;i<n;i++){
        ll m1 = max(dp[i-1],dp[i-2]) ;
        dp[i] = a[i]+ max(m1,dp[i-3]) ;
    }
    cout << dp[n-1] ;
}