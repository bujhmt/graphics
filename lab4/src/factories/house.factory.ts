import {
    BoxBufferGeometry,
    CircleBufferGeometry,
    ConeBufferGeometry,
    CylinderBufferGeometry,
    Group,
    Mesh,
    MeshBasicMaterial,
    MeshLambertMaterial,
} from 'three';
import {WindowFactory} from './window.factory';

export function HouseFactory(): Group {
    const house = new Group();

    const main = new Mesh(
        new BoxBufferGeometry(180, 230, 180),
        new MeshLambertMaterial({color: 0x91651d}),
    );

    const roof = new Mesh(
        new ConeBufferGeometry(200, 120, 4),
        new MeshLambertMaterial({color: 0xc24d04}),
    );
    roof.position.set(0, 150, 0);
    roof.rotateY(0.800);

    const window1 = WindowFactory();
    window1.position.set(91, 0, 0);
    window1.rotateY(55);

    const window2 = WindowFactory();
    window2.position.set(-91, 0, 0);
    window2.rotateY(55);

    const chimney = new Mesh(
        new CylinderBufferGeometry(30, 30, 80),
        new MeshBasicMaterial({color: 0x4f3e34}),
    );
    chimney.position.set(-45, 180, -45);

    const hobbitDoor = new Mesh(
        new CircleBufferGeometry(60, 6),
        new MeshBasicMaterial({color: 0x5d2906}),
    )
    hobbitDoor.position.set(0, -20, 90.5);

    house.add(
        main,
        roof,
        window1,
        window2,
        chimney,
        hobbitDoor,
    );

    return house;
}
