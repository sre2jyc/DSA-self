# include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;
typedef pair<ll,ll> Pll ;

ll prims(vector<vector<pair<ll,ll> > >&G,vector<ll> &min_val,vector<ll> &check,priority_queue<Pll , vector<Pll> , greater<Pll> > &Q){

  while(Q.size()!=0){
    Pll p = Q.top() ;
    Q.pop() ;
    if(check[p.second] == -1){
        check[p.second] = 0 ;
        for(ll i=0;i<G[p.second].size();i++){
            if(check[G[p.second][i].first]==-1){
                min_val[G[p.second][i].first] = min(min_val[G[p.second][i].first],G[p.second][i].second) ;
                Q.push(make_pair(min_val[G[p.second][i].first],G[p.second][i].first));
            }
        }


    }
  }

  ll min_sum = accumulate(min_val.begin(),min_val.end(),0) ;
  return min_sum ;
}







int main(){
    ll n,e ;
    cin >> n >> e ;
    vector<vector<pair<ll,ll> > >G ; // 1st field contains adjacent node, 2nd field contains edge
    G.resize(n+1) ;
    ll x,y,w ;
    for(ll i=0;i<e;i++){
        cin >> x >> y>>w ;
        G[x-1].push_back(make_pair(y-1,w)) ;
        G[y-1].push_back(make_pair(x-1,w)) ;
    }
    vector<ll> min_val(n,9999999999) , check(n,-1) ;
    priority_queue<Pll , vector<Pll> , greater<Pll> > Q ;
    ll starting_node ;
    cin >> starting_node ;
    min_val[starting_node] = 0 ;
    Q.push(make_pair(min_val[starting_node],starting_node)) ;

    ll min_sum = prims(G,min_val,check,Q) ;
    cout << min_sum <<"\n" ;
}






//BASIC IDEA IS I TAKE A STARTING NODE , PUSH IT IN PRIORITY QUEUE Q , WITH ITS MIN_VALUE = 0 . 
// WHILE Q IS NOT EMPTY : I TAKE TOP MOST NODE, AND CHECK IT TO 0 , THEN TAKE ALL ITS ADJACENT NODES HAVING CHECK = -1 .
// MIN_VAL[ADJACENT_NODE] = MIN(MIN_VAL[ADJACENT_NODE],EDGE_WEIGHT)











/*
5 7
1 1 2
7 1 3
5 2 3
3 2 5
6 3 5
4 2 4
2 4 5
*/