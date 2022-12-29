import './ExpenseItem.css'
function ExpenseItem() {
    const expenseDate = new Date(2022, 12, 29);
    const expenseTitle = 'Car Insurrr';
    const expenseAmount = 123123.444;
    return (
        <div className="expense-item">
            <div>{expenseDate.toDateString()}</div>
            <div className="expense-item__description">
                <h2> {expenseTitle}</h2>
                <div  className="expense-item__price"> {expenseAmount}$</div>
            </div>
            <h2>This is Expense component</h2>

        </div>
    );
}
export default ExpenseItem;