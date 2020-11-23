import React from "react";
import { useFormik } from "formik";

interface IProps {
    searchCat:(subname: string, fromDate: string, toDate: string) => void;
}

export const SearchFormWithDate: React.FC<IProps> = ({searchCat}) => {
    const formik = useFormik({
        initialValues: {
            catName: '',
            formDate: '1999-09-12',
            toDate: '2010-09-12',
        },
        onSubmit: values => {
            searchCat(formik.values.catName, formik.values.formDate, formik.values.toDate);
        },
    });
    return (
        <form onSubmit={formik.handleSubmit}>
            <label htmlFor="catName">Cat Name (or Subname)</label>
            <input
                id="catName"
                name="catName"
                type="text"
                onChange={formik.handleChange}
                value={formik.values.catName}
            />
            <label htmlFor="lastName">From Date</label>
            <input
                id="fromDate"
                name="formDate"
                type="text"
                onChange={formik.handleChange}
                value={formik.values.formDate}
            />
            <label htmlFor="email">To Date</label>
            <input
                id="toDate"
                name="toDate"
                type="text"
                onChange={formik.handleChange}
                value={formik.values.toDate}
            />
            <button type="submit">Submit</button>
        </form>
    );
}