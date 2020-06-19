# include<bits/stdc++.h>
using namespace std ;
typedef long long int ll ;

ll root_find(vector<ll>&root,ll val){
    while(root[val]!=val){
        val = root[val] ;
    }
    return val ;
}

void root_union(vector<ll>&root,vector<ll>&tree_size,ll a,ll b){

    ll ra = root_find(root,a) ;
    ll rb = root_find(root,b) ;
    if(tree_size[ra]>tree_size[rb]){
        root[rb] = ra ;
        tree_size[ra] += tree_size[rb] ;
    }
    else{
        root[ra]=rb ;
        tree_size[rb] += tree_size[ra] ;
    }
}

int main(){
    ll n,e ;
    cin >> n >> e ;
    vector <pair<ll,pair<ll,ll> > > G ;
    ll edgewt,a,b ;
    for(ll i=0;i<e;i++){
        cin >> edgewt >> a >> b ;
        G.push_back(make_pair(edgewt,make_pair(a,b))) ;
    }
    vector <ll> root(n,-1) , tree_size(n,1) ;
    for(ll i=0;i<n;i++){
        root[i]=i ;
    }
    ll min_cost = 0 ;
    sort(G.begin(),G.end()) ;
    // for(ll i=0;i<e;i++){
    //     cout << G[i].first <<" "<<G[i].second.first << " "<<G[i].second.second <<"\n" ;
    // }
    for(ll i=0;i<e;i++){
        ll r1 = root_find(root,G[i].second.first-1) ;
        ll r2 = root_find(root,G[i].second.second-1) ;
        if(r1 != r2){
           root_union(root,tree_size,G[i].second.first-1,G[i].second.second-1) ;
           min_cost += G[i].first ;
        }
    }
    cout << min_cost <<"\n" ;
    


}


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
