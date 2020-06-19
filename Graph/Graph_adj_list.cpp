# include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;

void addEdge(vector<vector<ll> > &graph , ll u,ll v){
    graph[u].push_back(v) ;
    graph[v].push_back(u) ;
}

void printGraph(vector<vector<ll> > &graph,ll n){
    for(ll i=0;i<n;i++){
        cout << "\n Edge "<<i ;
        for(ll j=0;j<graph[i].size();j++){
            cout <<"-> "<<graph[i][j] ;
        }
    }
}


int main(){
    ll n=5 ;
    // cin >> n ;
    vector <vector<ll> > graph ;
    graph.resize(n+1) ;
    addEdge(graph, 0, 1); 
    addEdge(graph, 0, 4); 
    addEdge(graph, 1, 2); 
    addEdge(graph, 1, 3); 
    addEdge(graph, 1, 4); 
    addEdge(graph, 2, 3); 
    addEdge(graph, 3, 4);
    printGraph(graph, n);

}
