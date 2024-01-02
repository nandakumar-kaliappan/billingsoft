console.log("welcome (Fetched)");
let customers;
let products;
let orders;
let currentCustomer = null;
let currentProduct = null;
let orderHeader = {
    id: null,
    customer: null,
    orderLines: [],
    count: 0,
    amount: 0
}
let orderLine = {
    id: null,
    quantity: 0,
    product: null,
    orderHeader: null
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
const productInput = document.querySelector('.productInput');
const productIdOptions = document.getElementById('productIdOptions');
const rateBanner = document.querySelector('.rateBanner');
const quantityInput = document.querySelector('.quantityInput');
const priceBanner = document.querySelector('.priceBanner');
const btnAddItem = document.querySelector('.btnAddItem');
const itemsTable = document.querySelector('.itemsTable');
const btnPlaceOrder = document.querySelector('.btnPlaceOrder');
const orderPlacementStatus = document.querySelector('.orderPlacementStatus');

const updateOrderHeaderCountAndAmount = function () {
    orderHeader.count = orderHeader.orderLines.length;
    orderHeader.amount = orderHeader.orderLines.reduce((acc, ol) => acc + ol.quantity * ol.product.rate, 0);
};
const updateOrderInfo = function () {
    updateOrderHeaderCountAndAmount();
    orderType.textContent = orderHeader.id ? 'Updating order' : 'New Order';
    itemCount.textContent = `${orderHeader.count} item(s)`;
    totalPrice.textContent = `${orderHeader.amount} Rs`;

    while (itemsTable.rows.length > 1)
        itemsTable.deleteRow(1);

    orderHeader.orderLines.forEach(ol=>{
        let row = itemsTable.insertRow(1);
        row.insertCell(0).innerText = `${ol.product.name}(#${ol.product.id})`;
        row.insertCell(1).innerText = `${ol.product.rate}`;
        row.insertCell(2).innerText = `${ol.quantity}`;
        row.insertCell(3).innerText = `${ol.product.rate*ol.quantity}`;
        row.insertCell(4).innerHTML =`<button onclick="editTableData(${ol.product.id})">Edit</button>
<button onclick="deleteTableData(${ol.product.id})">Delete</button>`;

    })
};

const deleteTableData = function (productId) {
    orderHeader.orderLines = orderHeader.orderLines.filter(ol=>ol.product.id != productId);
    updateOrderInfo();
}

const editTableData = function (productId) {
    console.log("edit called 1 "+productId);
    console.log(orderHeader);
    orderHeader.orderLines.forEach(ol=>{
        console.log(ol);
        console.log("Product id "+productId);
        console.log("ol.product.id "+ol.product.id);
        console.log(ol.product.id==productId);
        if(ol.product.id == productId){
            console.log("Entered if "+productId);
            productInput.value = productId;
            quantityInput.value = ol.quantity;
            rateBanner.textContent = `x ${ol.product.rate}Rs`;
            priceBanner.textContent = `= ${ol.product.rate* Number(quantityInput.value)}Rs`;
        }
    })
};
const updateProducts = function () {
    fetch("/api/v1/product")
        .then(respose => respose.json())
        .then(data => {
            console.log("fetching products");
            products = data;
            console.log(products);
            products.forEach(product => {
                const optionElement = document.createElement('option');
                optionElement.value = `${product.id}`;
                optionElement.text = `${product.name}(${product.rate} Rs)`;
                productIdOptions.appendChild(optionElement);
            })
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
    });
    if (currentCustomer) {
        console.log(`Current Customer`);
        console.log(currentCustomer);
        customerName.textContent = `${currentCustomer.id}  ${currentCustomer.name}`;
        customerPhone.textContent = `${currentCustomer.phone}`;
        customerAddress.textContent = `${currentCustomer.address}`;

        updateOrderInfo();

    } else {
        console.log(`No customer Found Check the Customer Id`);
        customerName.textContent = `Check The Id You Entered`;
        customerPhone.textContent = ``;
        customerAddress.textContent = ``;
        orderType.textContent = ``;
        itemCount.textContent = ``;
        totalPrice.textContent = ``;
    }
});

productInput.addEventListener('change', function () {
    currentProduct = null;
    products.forEach(product => {
        if (product.id == productInput.value) {
            currentProduct = product
        }
    });
    if (currentProduct) {
        quantityInput.value = '1';
        rateBanner.textContent = `x ${currentProduct.rate}Rs`;
        priceBanner.textContent = `= ${currentProduct.rate * Number(quantityInput.value)}Rs`;
    } else {
        quantityInput.value = '';
        rateBanner.textContent = ``;
        priceBanner.textContent = ``;
    }

});

quantityInput.addEventListener('change', function () {
    if (currentProduct)
        priceBanner.textContent = `= ${currentProduct.rate * Number(quantityInput.value)}Rs`;
})

btnAddItem.addEventListener("click", function () {
    if (currentProduct) {
        orderLine = {
            quantity: 0,
            product: null,
            orderHeader: null
        }
        orderLine.product = currentProduct;
        orderLine.quantity = Number(quantityInput.value);
        let isItemAlreadyAvailable = false
        orderHeader.orderLines.forEach(ol => {
            if (ol.product.id == orderLine.product.id) {
                isItemAlreadyAvailable = true;
                ol.quantity = orderLine.quantity;
            }
        });
        if (!isItemAlreadyAvailable) {
            orderHeader.orderLines.push(orderLine);
        }
        quantityInput.value = '';
        rateBanner.textContent = ``;
        priceBanner.textContent = ``;
        productInput.value = '';
        updateOrderHeaderCountAndAmount();
        updateOrderInfo();
        console.log(orderHeader);
        currentProduct = null;
    }

});

btnPlaceOrder.addEventListener('click',function () {
    console.log(orderHeader);
    orderHeader.customer=currentCustomer;
    if(orderHeader.orderLines.length && orderHeader.customer){
        orderPlacementStatus.textContent = `Ok`;
    }
    else{
        orderPlacementStatus.textContent = `Missing Detail`;
    }
})

updateOrderInfo();