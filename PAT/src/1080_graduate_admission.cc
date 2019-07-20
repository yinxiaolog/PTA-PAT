#include <iostream>
#include <set>
#include <vector>
#include <algorithm>
#include <climits>
#include <cmath>

using namespace std;

const int kSize = 101;

struct Applicant {
    int id;
    int ge;
    int gi;
    int total;
    int rank;
    vector<int> schools_id;

    Applicant(int _id, int _ge, int _gi) {
        id = _id;
        ge = _ge;
        gi = _gi;
        total = _ge + _gi;
        rank = -1;
    }
};

struct School {
    int id;
    int quota;
    int max_rank = INT_MIN;
    set<int> applicants_id;

    School() {
        id = quota = -1;
    };

    School(int _id, int _quota) {
        id = _id;
        quota = _quota;
    }
};

School schools[kSize];
vector<Applicant> applicants;

bool Cmp(Applicant &one, Applicant &another) {
    if (one.total != another.total) {
        return one.total > another.total;
    }

    return one.ge > another.ge;
}

void Solve() {
    sort(applicants.begin(), applicants.end(), Cmp);
    applicants[0].rank = 1;
    for (int i = 1; i < applicants.size(); i++) {
        if (applicants[i].total == applicants[i - 1].total && applicants[i].ge == applicants[i - 1].ge) {
            applicants[i].rank = applicants[i - 1].rank;
        } else {
            applicants[i].rank = i + 1;
        }
    }

    for (Applicant &applicant : applicants) {
        for (int school : applicant.schools_id) {
            if (schools[school].quota > 0) {
                schools[school].quota--;
                schools[school].applicants_id.insert(applicant.id);
                schools[school].max_rank = max(schools[school].max_rank, applicant.rank);
                break;
            } else if (schools[school].quota == 0) {
                if (applicant.rank == schools[school].max_rank) {
                    schools[school].applicants_id.insert(applicant.id);
                    break;
                }
            }
        }
    }
}

int main() {
    int N, M, K;
    cin >> N >> M >> K;
    for (int i = 0; i < M; i++) {
        int quota;
        cin >> quota;
        schools[i] = School(i, quota);
    }

    for (int i = 0; i < N; i++) {
        int ge, gi;
        cin >> ge >> gi;
        Applicant applicant = Applicant(i, ge, gi);
        for (int j = 0; j < K; j++) {
            int school;
            cin >> school;
            applicant.schools_id.push_back(school);
        }

        applicants.emplace_back(applicant);
    }

    Solve();

    for (int i = 0; i < M; i++) {
        if (schools[i].applicants_id.empty()) {
            cout << endl;
        } else {
            for (auto it = schools[i].applicants_id.begin(); it != schools[i].applicants_id.end(); it++) {
                if (it == schools[i].applicants_id.begin()) {
                    cout << *it;
                } else {
                    cout << " " << *it;
                }
            }
            cout << endl;
        }
    }
    return 0;
}