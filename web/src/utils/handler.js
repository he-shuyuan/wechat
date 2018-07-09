export const areaDataHandler = (res) => {
    let options = res.map(item => {
        let option = {
            value: item.areaCode,
            label: item.areaName,
            children: [],
        }
        if (item.subArea.length > 0) {
            option.children = item.subArea.map(_item =>
                ({
                    value: _item.areaCode,
                    label: _item.areaName,
                })
            )
        } else {
            option.children.push({
                value: '',
                label: '全部',
            })
        }
        return option
    })
    return options
}