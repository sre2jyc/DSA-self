# include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;


int main(){
    ll n,e ;
    cin >> n >> e ;
    vector <vector<ll> > G ;
    G.resize(n+1) ;
    for(ll i=0;i<n;i++){
        G[i].resize(n) ;
        for(ll j=0;j<n;j++){
            if(i==j){
                G[i][j] = 0  ;
            }
            else{
                G[i][j] = 999999999999 ;
            }
        }
    }

    ll x,y,w ;
    for(ll i=0;i<e;i++){
        cin >> x >>y >>w ;
        G[x-1][y-1] = w ;
        G[y-1][x-1] = w ;
    }

    for(ll k=0;k<n;k++){
        for(ll i=0;i<n;i++){
            for(ll j=0;j<n;j++){
                if(G[i][j]>(G[i][k]+G[k][j])){
                    G[i][j] = G[i][k]+G[k][j] ;
                }
            }
        }
    }

     for(ll i=0;i<n;i++){
        for(ll j=0;j<n;j++){
            cout << G[i][j] <<" " ;
        }
        cout << "\n" ;
    }

}