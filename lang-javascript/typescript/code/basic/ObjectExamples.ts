/**
 * Created by sujayjayaram on 02/02/2020.
 */

let obj = {
    name: 'Krunal',
    education: 'IT Engineer'
} ;
console.log(Object.keys(obj));

// This method requires that the object already exists else it won't compile
//obj.name2 = 'fred'; // Won't compile
obj.name = 'fred';
obj["name2"] = 'tony'; // This will work
console.log(Object.keys(obj));
console.log(Object.keys(obj).length)


// Iterate keys
Object.keys(obj).forEach(item => {
    console.log(item);
});

console.log('*******************');

// Sorting
let unorderedData = {
    real_name: 'Millie Bobby Brown',
    character_name: 'Eleven',
    series: 'Stranger Things'
};
console.log(JSON.stringify(unorderedData));

const orderedData = {};
Object.keys(unorderedData).sort().forEach(function(key) {
    // This is setting a name value pair!
    // Read carefully - it's deceptive
    orderedData[key] = unorderedData[key];
});

console.log(JSON.stringify(orderedData));

// List view of keys along with index
// https://appdividend.com/2018/12/27/javascript-object-keys-example-object-keys-tutorial/
Object.keys(orderedData).map(function (old_key, index) {
    console.log('old_key=' + old_key);
    console.log('index=' + index);
});