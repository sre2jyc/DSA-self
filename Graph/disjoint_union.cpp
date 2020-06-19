# include <bits/stdc++.h>
using namespace std ;
typedef long long int ll ;

ll root_find(vector<ll> &root, ll val){
  while(root[val] != val){
      val = root[val] ;
  }
  return val ;
}

void root_union(vector<ll> &root, vector<ll> &size_tree,ll a,ll b){
    ll root_a = root_find(root,a) ;
    ll root_b = root_find(root,b) ;
    if(size_tree[root_a] > size_tree[root_b]){
        root[root_b] = root_a ;
        size_tree[root_a] += size_tree[root_b] ;

    }
    else{
        root[root_a] = root_b ;
        size_tree[root_b] += size_tree[root_a] ;
    }
}



int main(){


    ll n ;
    cin >> n ;

    // initialising
    vector<ll> root(n,-1) , tree_size(n,1)  ;
    for(ll i=0;i<n;i++){
        root[i] = i ;
    }

    // root finder
    ll value_to_find ;
    cin >> value_to_find ;

    ll rf = root_find(root,value_to_find) ;
    cout<< rf ;

    // making union
    ll q ;
    cin>> q ;
    for(ll i=0;i<q;i++){
    ll a,b;
    cin >>a>>b ;
    root_union(root,tree_size,a,b) ;
    }
    for(ll i=0;i<n;i++){
        cout << root[i] <<" "<<tree_size[i] <<"\n" ;
    }

    //checking if two random nodes are disjoint or not 
    ll v1,v2 ;
    cin >> v1 >> v2 ;
    ll rv1 = root_find(root,v1) ;
    ll rv2 = root_find(root,v2) ;
    if(rv1==rv2){
        cout << "CONNECTED" <<"\n" ;
    }
    else{
        cout << "DISJOINT" <<"\n" ;
    }



}