import React, {useEffect, useState} from 'react';
import axios from "axios";
import {SearchFormWithDate} from "./SearchFormWithDate";
import {SearchFormOnlySubStringInName} from "./SearchFormOnlySubStringInName";

const api = axios.create({
    baseURL: `http://localhost:8080/cat`
})

interface ICat {
    id: string;
    name: string;
    mother: string;
    father: string;
    sold: string;
    color: string;
}

const App: React.FC = () => {
    const [cats, setCats] = useState<Array<ICat | null> | null>([]);
    const [catsSearch, setCatsSearch] = useState<Array<string | null> | null>([]);

    useEffect(() => {
        getAllCats();
    }, []);

    const getAllCats = () => {
        api.get('/')
            .then((res) => {
                const cats: Array<ICat> = res.data;
                setCats(cats);
            })
            .catch((err: any) => console.log(err));
    };

    const searchCatsWithDate = (subname: string, fromDate: string, toDate: string) => {
        api.get(`/search/sub/${subname}/from/${fromDate}/to/${toDate}/sort`)
            .then((res) => {
                const cats: Array<string> = res.data;
                setCatsSearch(cats);
            })
            .catch((err: any) => console.log(err));
    }

    const searchCatsBySubStringInName = (subname: string) => {
        api.get(`/search/sub/${subname}`)
            .then((res) => {
                const cats: Array<string> = res.data;
                setCatsSearch(cats);
            })
            .catch((err: any) => console.log(err));
    }

    return (
        <div>
            <ul>
                {cats?.map(cat => (
                    <li key={cat?.id}>{cat?.name} ==-=-== {cat?.sold}</li>
                ))}
            </ul>
            <SearchFormOnlySubStringInName searchCat={searchCatsBySubStringInName}/>
            <SearchFormWithDate searchCat={searchCatsWithDate}/>
            <ul>
                {catsSearch?.map(cat => (
                    <li key={cat}>{cat}</li>
                ))}
            </ul>
        </div>);
}

export default App;
