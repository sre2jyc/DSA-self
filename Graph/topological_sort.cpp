# include<bits/stdc++.h>
using namespace std ;
typedef long long int ll ;


void addEdge(vector<vector<ll> > &graph,ll u, ll v){
    graph[u].push_back(v) ;
    graph[v].push_back(u) ;
}

void topological_sort(vector<vector<ll> > &graph, ll starting ){
    
} 



int main(){
    ll n ;
    cin >> n ;
    vector<vector<ll> > graph ;
    graph.resize(n+1) ;

}