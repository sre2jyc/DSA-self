# include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;

int main(){
  ll n;
  cin >> n ;
  vector <ll> a(n) ;
  for(ll i=0;i<n;i++){
      cin >> a[i] ;
  }
  vector <ll> dp(n,0) ;
  dp[0] = a[0];
  dp[1] = max(dp[0],a[1]) ;
  for(ll i=2;i<=n;i++){
      dp[i] = max((a[i]+dp[i-2]),dp[i-1]) ;
  }

  cout << dp[n-1] ;


}