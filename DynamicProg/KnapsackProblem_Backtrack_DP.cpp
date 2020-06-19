#include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;

ll backtrack_KnapSack(ll c,ll i,ll n,vector<ll> &W,vector<ll> &V){
    if(i>=n){
        return 0 ;
    }
    
    if(c<W[i]){
        return backtrack_KnapSack(c,i+1,n,W,V) ;
    }
    else{
          
          return max(backtrack_KnapSack(c,i+1,n,W,V), V[i]+backtrack_KnapSack(c-W[i],i+1,n,W,V) ) ;



    }

}

ll dp_approach(vector<ll> &W,vector <ll> &V,ll c, ll n){
    ll dp[n+1][c+1] ;
    for(ll i=0;i<n+1;i++){
        for(ll j=0;j<c+1;j++){
            if(i==0||j==0){
                dp[i][j] = 0 ;
            }
        }
    }

    for(ll i=1;i<n+1;i++){
        for(ll j=1;j<c+1;j++){
            if(j>=W[i-1]){
                dp[i][j] = max(V[i-1]+dp[i-1][j-W[i-1]],dp[i-1][j]) ;
            }
            else{
                dp[i][j] = dp[i-1][j] ;
            }
        }
    }

    return dp[n][c] ;
}

 
int main(){
  ios_base::sync_with_stdio(false);
    cin.tie(NULL);

   ll n ;
vector <ll> V(n),W(n) ;
   cin >> n ;
   
   for(ll i=0;i<n;i++){
       cin >> V[i] ;
   }
   for(ll i=0;i<n;i++){
       cin >> W[i] ;
   }
   ll c ;
   cin >> c ;
   ll value = backtrack_KnapSack(c,0,n,W,V) ;
   cout << value <<"\n" ;

   vector<vector<ll> > table ; 
   table.resize(n+1) ;
   for(ll i=0;i<n+1;i++){
       table[i].resize(c+1) ;
   }

   for(ll i=0;i<table.size();i++){
       for(ll j=0;j<table[i].size();j++){
           table[i][j] = -1 ;
       }
   }
   
   ll x = dp_approach(W,V,c,n) ;
   cout << x <<"\n" ;
   
   

   
     
    
return 0 ;
}