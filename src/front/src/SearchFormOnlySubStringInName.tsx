import React from "react";
import {useFormik} from "formik";

interface IProps {
    searchCat: (subString: string) => void;
}

export const SearchFormOnlySubStringInName: React.FC<IProps> = ({searchCat}) => {
    const formik = useFormik({
        initialValues: {
            catSubStringName: ''
        },
        onSubmit: values => {
            searchCat(formik.values.catSubStringName);
        },
    });
    return (
        <form onSubmit={formik.handleSubmit}>
            <label htmlFor="catSubStringName">Cat Name (or Subname)</label>
            <input
                id="catSubStringName"
                name="catSubStringName"
                type="text"
                onChange={formik.handleChange}
                value={formik.values.catSubStringName}
            />

            <button type="submit">Submit</button>
        </form>
    );
}