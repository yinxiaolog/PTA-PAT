#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

struct Book {
    int id;
    string title;
    string author;
    string key;
    string publisher;
    int year;

    Book(int _id, string _title, string _author, string _key, string _publisher, int _year) {
        id = _id;
        title = _title;
        author = _author;
        key = _key;
        publisher = _publisher;
        year = _year;
    }
};

vector<Book> books;

vector<int> QueryTitle(const string &t) {
    vector<int> ans;
    for (Book book : books) {
        if (book.title == t) {
            ans.push_back(book.id);
        }
    }

    sort(ans.begin(), ans.end());
    return ans;
}

vector<int> QueryAuthor(const string &name) {
    vector<int> ans;
    for (Book book : books) {
        if (book.author == name) {
            ans.push_back(book.id);
        }
    }

    sort(ans.begin(), ans.end());
    return ans;
}

vector<int> QueryKey(const string &key) {
    vector<int> ans;
    for (Book book : books) {
        if (book.key.find(key) != string::npos) {
            ans.push_back(book.id);
        }
    }

    sort(ans.begin(), ans.end());
    return ans;
}

vector<int> QueryPublisher(const string &publisher) {
    vector<int> ans;
    for (Book book : books) {
        if (book.publisher == publisher) {
            ans.push_back(book.id);
        }
    }

    sort(ans.begin(), ans.end());
    return ans;
}

vector<int> QueryYear(int year) {
    vector<int> ans;
    for (Book book : books) {
        if (book.year == year) {
            ans.push_back(book.id);
        }
    }

    sort(ans.begin(), ans.end());
    return ans;
}

vector<int> Query(int query, const string &s) {
    switch (query) {
        case 1 :
            return QueryTitle(s);
        case 2 :
            return QueryAuthor(s);
        case 3 :
            return QueryKey(s);
        case 4 :
            return QueryPublisher(s);
        case 5 :
            return QueryYear(stoi(s));
    }
}


int main() {
    int N;
    string s;
    getline(cin, s);

    N = stoi(s);
    for (int i = 0; i < N; i++) {
        string id, title, author, key, publisher, year;

        getline(cin, id);
        getline(cin, title);
        getline(cin, author);
        getline(cin, key);
        getline(cin, publisher);
        getline(cin, year);

        books.emplace_back(Book(stoi(id), title, author, key, publisher, stoi(year)));
    }

    int M;
    getline(cin, s);
    M = stoi(s);
    for (int i = 0; i < M; i++) {
        string query;
        getline(cin, query);
        vector<int> ans = Query(stoi(query.substr(0, 1)), query.substr(3));
        cout << query << endl;
        for (int i : ans) {
            printf("%07d\n", i);
        }
        if (ans.empty()) {
            cout << "Not Found" << endl;
        }
    }
    return 0;
}