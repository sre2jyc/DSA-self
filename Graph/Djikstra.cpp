# include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;
typedef pair<ll,ll> Pll ;
typedef vector<vector<Pll> > VVP ;
typedef priority_queue<Pll, vector<Pll> , greater<Pll> > RevQP ;


void Djikstra(VVP &G,RevQP &Q,vector<Pll> &S,vector<ll> &T){
    while(Q.size()!= 0){
        Pll t = Q.top() ;
        Q.pop() ;
        ll curr_root = t.second ;
        ll curr_root_wt = t.first ;
        for(ll i=0;i<G[curr_root].size();i++){
          ll branch_node = G[curr_root][i].first ;
          ll edge_wt = G[curr_root][i].second ;
          if(S[branch_node].first > (edge_wt+curr_root_wt)){
              S[branch_node].first = edge_wt+curr_root_wt ;
              // S[branch_node].second = curr_root ;
              T[branch_node] = curr_root ;
              Q.push(S[branch_node]) ;
          }
        //   else{
        //       continue ;
        //   }
        }
    }
}



int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    ll n ,e ;
    cin >> n >> e ;
   // vector<vector<pair<ll,ll> > > G  ;
    VVP G ;
    G.resize(n+1) ;
    ll x,y,w ;
    for(ll i=0;i<e;i++){
        cin>>x>>y>>w ;
        G[x-1].push_back(make_pair(y-1,w)) ;
    }
    // for(ll i=0;i<n;i++){
    //     // cin>>x>>y>>w ;
    //    // G[x-1].push_back(make_pair(y-1,w)) ;
    //    cout << i+1<<" " ;
    //    for(ll j=0;j<G[i].size();j++){
    //        cout << G[i][j].first+1 <<" " ;
    //    }
    //    cout <<"\n" ;
    // }
    vector <Pll > S ;
    for(ll i=0;i<n;i++){
        S.push_back(make_pair(999999999999,i)) ; // s stores wt and node itself
    }
    //priority_queue<Pll , vector<Pll> , greater<Pll> > Q ;
    vector <ll> Track(n,-1) ; // track stores the path
    RevQP Q ;
    ll source ;
    cin >> source ;
    S[source-1].first = 0 ;
    // S[source-1].second = source-1 ;
    Track[source-1] = source-1 ;
    
    // for(ll i=0;i<n;i++){
    //     cout << S[i].first <<" "<<S[i].second <<"\n" ; 
    // }

    Q.push(S[source-1]) ;
    Djikstra(G,Q,S,Track) ;

    for(ll i=0;i<n;i++){
        cout << S[i].first <<" "<<S[i].second <<"\n" ; 
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