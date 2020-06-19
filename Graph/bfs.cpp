# include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;

void addEdge(vector<vector<ll> > &graph , ll u,ll v){
    graph[u].push_back(v) ;
    graph[v].push_back(u) ;
}

void bfs(vector<vector<ll> > &graph , ll starting){
    queue<ll> Q ;
    vector <ll> check(graph.size()+1,0) ;
    Q.push(starting) ;
    while(Q.size() != 0){
       ll x = Q.front() ;
       Q.pop() ;
       for(ll i=0;i<graph[x].size();i++){
           Q.push(graph[x][i]) ;
       }
       if(check[x]==0){
           cout<<x <<" " ;
           check[x] = 1 ;
       }

    }
}


int main(){
    ll n ;
    cin >> n ;
    vector<vector<ll> > graph ;
    graph.resize(n+1) ;
    addEdge(graph,0, 1); 
    addEdge(graph,0, 2); 
    addEdge(graph,1, 2); 
    addEdge(graph,2, 0); 
    addEdge(graph,2, 3); 
    addEdge(graph,3, 3); 

    bfs(graph,2) ;

    
}