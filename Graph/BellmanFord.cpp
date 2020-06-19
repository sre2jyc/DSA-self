# include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;
typedef pair<ll,ll> Pll ; 
typedef vector<vector<Pll> > VV ;

void BellmanFord(VV &G,vector<Pll> &root){
  for(ll q=0;q<G.size()-1;q++){
      for(ll i=0;i<G.size();i++){
          for(ll j=0;j<G[i].size();j++){
              ll branch_node = G[i][j].first ;
              ll edge_wt = G[i][j].second ;
              if(root[branch_node].second > (edge_wt + root[i].second)){
                  root[branch_node].first = i ;
                  root[branch_node].second = edge_wt + root[i].second ;
              }
          }
      }
  }
}


int main(){
    ll n,e ;
    cin >> n >> e ;
    VV G ;
    G.resize(n+1) ;
    ll from , to , weight ;
    for(ll i=0;i<e;i++){
        cin >> from >> to >> weight ;
        G[from-1].push_back(make_pair(to-1,weight)) ;
    }
    vector<Pll> root ;
    for(ll i=0;i<n;i++){
      root.push_back(make_pair(-1,999999999999)) ;
    }
    ll source ;
    cin>>source ;
    root[source-1].first = source-1 ;
    root[source-1].second = 0 ;

    BellmanFord(G,root) ;

    for(ll i=0;i<root.size();i++){
        cout << root[i].first <<" "<<root[i].second <<"\n" ;
    
    }



}

/*
5 5
1 2 5
1 3 2
3 4 1
1 4 6
3 5 5
*/