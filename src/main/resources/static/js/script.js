
console.log("welcome (Fetched)");
let customers;
let products;
let orders;
let currentCustomer = null;
let CurrentOrderHeader = {
    customer:null,
    orderLines:[],
    count:0,
    amount:0
}

const orderIdInput = document.querySelector('.orderIdInput');
const orderIdOptions = document.querySelector('orderIdOptions');
const btnOrderId = document.querySelector('.btnOrderId');
const customerInput = document.querySelector('.customerInput');
const customerIdOptions = document.getElementById('customerIdOptions');
const customerName = document.querySelector('.customerName');
const customerPhone = document.querySelector('.customerPhone');
const customerAddress = document.querySelector('.customerAddress');
const orderType = document.querySelector('.orderType');
const itemCount = document.querySelector('.itemCount');
const totalPrice = document.querySelector('.totalPrice');


const updateProducts = function () {
    fetch("/api/v1/product")
        .then(respose => respose.json())
        .then(data => {
            console.log("fetching products");
            products = data;
            console.log(products);
        })
        .catch(error => {
            console.error('Error fetching products:', error);
        });
}

const updateCustomersAndCustomerDropDown = function () {
    fetch("/api/v1/customer")
        .then(respose => respose.json())
        .then(data => {
            customers = data;
            // console.log("fetching customers individually");
            customers.forEach(customer => {
                // console.log(`> ${customer.id}  ${customer.name} (phn -${customer.phone})`)
                const optionElement = document.createElement('option');
                optionElement.text = `${customer.name} (phn -${customer.phone})`;
                optionElement.value = `${customer.id}`;
                customerIdOptions.appendChild(optionElement);
            })
        })
        .catch(error => {
            console.error('Error fetching customers:', error);
        });
}

const updateOrders = function () {
    fetch("/api/v1/order")
        .then(respose => respose.json())
        .then(data => {
            orders = data;
            console.log("fetching orders");
            console.log(orders);
        })
        .catch(error => {
            console.error('Error fetching orders:', error);
        });
}

const getCustomer = function (id) {
    fetch(`/api/v1/customer/${id}`)
        .then(response => {
            console.log('Response:-');
            console.log(response);
            return response.json();
        })
        .then(data => {
            console.log('Data:-')
            console.log(data)
        })
}
// getCustomer(47);
updateCustomersAndCustomerDropDown();
updateProducts();
updateOrders();
console.log(orders);

btnOrderId.addEventListener("click", function () {
    console.log("-Button pressed-1");
    console.log(orders);
    orders.forEach(order => console.log(order));
})

customerInput.addEventListener('change', function () {
    console.log(`Entered: ${customerInput.value}`)
    currentCustomer = null;
    customers.forEach(customer => {
        if (customer.id == customerInput.value) {
            currentCustomer = customer;
        }
        if (currentCustomer) {
            console.log(`Current Customer`);
            console.log(currentCustomer);
            customerName.textContent = `${currentCustomer.id}  ${currentCustomer.name}`;
            customerPhone.textContent = `${currentCustomer.phone}`;
            customerAddress.textContent = `${currentCustomer.address}`;
            orderType.textContent = `New Order`;
            itemCount.textContent = `0 item(s)`;
            totalPrice.textContent = `0 Rs`;

        } else {
            console.log(`No customer Found Check the Customer Id`);
            customerName.textContent = `Check The Id You Entered`;
            customerPhone.textContent = ``;
            customerAddress.textContent = ``;
            orderType.textContent = ``;
            itemCount.textContent = ``;
            totalPrice.textContent = ``;
        }
    })
})
