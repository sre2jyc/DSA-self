// given an array and a sum S , find whether its possible to draw a sub set whose sum = S . return true or false


# include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;

bool subSum(vector<ll> &arr , ll S){
    ll n = arr.size() ;
    bool dp[n+1][S+1] ;
    for(ll i=0;i<=n;i++){
        for(ll j=0;j<S+1;j++){
            // if(i==j==0){
            //     dp[i][j] = true ;
            // }
             if(i==0){
                dp[i][j] = false ;
            }
             if(j==0){
                dp[i][j] = true ;
            }
        }

    }
    dp[0][0]=true ;


    for(ll i=1;i<n+1;i++){
        for(ll j=1;j<S+1;j++){
            if(j>=arr[i-1]){
                dp[i][j] = (dp[i-1][j-arr[i-1]] || dp[i-1][j]) ;
            }
            else{
                dp[i][j] = dp[i-1][j] ;
            }
        }
    }

    return dp[n][S] ;

}


int main(){
    ll n ;
    cin >> n ;
    vector<ll> arr(n) ;
    for(ll i=0;i<n;i++){
       cin >> arr[i] ;
    }
    ll S ;
    cin >> S ;

    if(subSum(arr,S)){
        cout << "possible" ;
    }
    else{
        cout << "impossible" ;
    }
}